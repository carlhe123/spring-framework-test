'use strict';

/* Controllers */

angular.module('app')
    .controller('AppCtrl', ['$scope', '$translate', '$localStorage', '$window', '$modalInstance', 'httper',
    function ($scope, $translate, $localStorage, $window, $modalInstance, httper) {
        // add 'ie' classes to html
        var isIE = !!navigator.userAgent.match(/MSIE/i);
        isIE && angular.element($window.document.body).addClass('ie');
        isSmartDevice($window) && angular.element($window.document.body).addClass('smart');

        $scope.cancel = function () {
            $modalInstance.dismiss('close');
        };

        $scope.submit = function () {
            var user = {
                username: $scope.username,
                password: $scope.password
            }
            httper.get("/login", user).success(function () {
                $scope.cancel();
                $rootScope.reload();
            });
        }

        // config
        $scope.app = {
            name: 'Angulr',
            version: '2.0.1',
            // for chart colors
            color: {
                primary: '#7266ba',
                info: '#23b7e5',
                success: '#27c24c',
                warning: '#fad733',
                danger: '#f05050',
                light: '#e8eff0',
                dark: '#3a3f51',
                black: '#1c2b36'
            },
            settings: {
                themeID: 1,
                navbarHeaderColor: 'bg-black',
                navbarCollapseColor: 'bg-white-only',
                asideColor: 'bg-black',
                headerFixed: true,
                asideFixed: false,
                asideFolded: false,
                asideDock: false,
                container: false
            }
        }

        // save settings to local storage
        if (angular.isDefined($localStorage.settings)) {
            $scope.app.settings = $localStorage.settings;
        } else {
            $localStorage.settings = $scope.app.settings;
        }
        $scope.$watch('app.settings', function () {
            if ($scope.app.settings.asideDock && $scope.app.settings.asideFixed) {
                // aside dock and fixed must set the header fixed.
                $scope.app.settings.headerFixed = true;
            }
            // save to local storage
            $localStorage.settings = $scope.app.settings;
        }, true);

        // angular translate
        $scope.lang = {isopen: false};
        $scope.langs = {en: 'English', de_DE: 'German', it_IT: 'Italian'};
        $scope.selectLang = $scope.langs[$translate.proposedLanguage()] || "English";
        $scope.setLang = function (langKey, $event) {
            // set the current lang
            $scope.selectLang = $scope.langs[langKey];
            // You can change the language during runtime
            $translate.use(langKey);
            $scope.lang.isopen = !$scope.lang.isopen;
        };

        function isSmartDevice($window) {
            // Adapted from http://www.detectmobilebrowsers.com
            var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
            // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
            return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
        }

    }]).factory("httper", ["logger", "$http", "$modal", function (logger, $http, $modal) {

    var httper = {};

    httper.log = function (data, status, msg) {
        if (status == 200) {
            if (data.respCode) {
                if (data.respCode == '1000') {
                    logger.logSuccess(msg);
                } else {
                    logger.logError(data.respMsg);
                }
            } else {
                logger.logSuccess(msg);
            }
        } else {
            logger.logError(data.respMsg);
        }
    }

    httper.confirmDialog = function (dialogTitle) {
        var dialog = {};
        dialog.title = dialogTitle ? dialogTitle : "是否确认提交?";
        dialog.html = '<div class="modal-body">' +
            '<h3>' + dialog.title + '</h3>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button class="btn btn-default" ng-click="cancel()">否</button>' +
            '<button class="btn btn-primary" ng-click="ok()">是</button>' +
            '</div>';
        dialog.okFunction = function () {
            return dialog;
        };
        dialog.ok = function (okfn) {
            dialog.okFunction = okfn;
            return dialog;
        }
        dialog.cancleFunction = function () {
            return dialog;
        };
        dialog.cancle = function (canclefn) {
            dialog.cancleFunction = canclefn;
            return dialog;
        }
        dialog.open = function () {
            var modalInstance = $modal.open({
                template: dialog.html,
                controller: function ($scope, $modalInstance) {
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    $scope.ok = function () {
                        $modalInstance.close("");
                    };
                }
            });
            modalInstance.result.then(dialog.okFunction, dialog.cancleFunction);
        }
        return dialog;
    };

    httper.confirmWithContentDialog = function (dialogTitle, content) {
        var dialog = {};
        dialog.title = dialogTitle ? dialogTitle : "是否确认提交?";
        dialog.html = '<div class="modal-body">' +
            '<h3>' + dialog.title + '</h3>' +
            '<span style="color: red">' + content + '</span>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button class="btn btn-default" ng-click="cancel()">否</button>' +
            '<button class="btn btn-primary" ng-click="ok()">是</button>' +
            '</div>';
        dialog.okFunction = function () {
            return dialog;
        };
        dialog.ok = function (okfn) {
            dialog.okFunction = okfn;
            return dialog;
        }
        dialog.cancleFunction = function () {
            return dialog;
        };
        dialog.cancle = function (canclefn) {
            dialog.cancleFunction = canclefn;
            return dialog;
        }
        dialog.open = function () {
            var modalInstance = $modal.open({
                template: dialog.html,
                controller: function ($scope, $modalInstance) {
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    $scope.ok = function () {
                        $modalInstance.close("");
                    };
                }
            });
            modalInstance.result.then(dialog.okFunction, dialog.cancleFunction);
        }
        return dialog;
    };

    httper.get = function (url, data, config) {
        return $http.get(absUrl(url + "?t=" + new Date().getTime()), data, config).error(httper.log);
    };

    httper.search = function (url, data, config) {
        if (data) {
            if (!data.pageQuery) {
                if (data.page && data.size) {
                    data.pageQuery = {};
                    data.pageQuery.page = data.page;
                    data.pageQuery.size = data.size;
                }
            }
            if (!data.conditions) {
                data.conditions = [];
                if (data.eqConditions) {
                    angular.forEach(data.eqConditions, function (field) {
                        if (data[field]) {
                            var condition = {};
                            condition.condition = field + ".EQ";
                            condition.value = data[field];
                            data.conditions.push(condition);
                        }
                    });
                }
            }
        }
        return $http.post(absUrl(url), data, config).error(httper.log);
    };

    httper.post = function (url, data, config) {
        return httper.submit(function () {
            return $http.post(absUrl(url), data, config).error(httper.log).success(function (data, status) {
                if (!config || !config.hideLogger) {
                    httper.log(data, status, "提交成功");
                }
            })
        });
    };

    httper.getJson = function (url, data, config) {
        return httper.submit(function () {
            return $http.post(absUrl(url), data, config).error(httper.log).success(function (data, status) {
                if (!config || !config.hideLogger) {
                }
            })
        });
    };

    httper.sendJson = function (url, data, config) {
        return httper.submit(function () {
            return $http.post(absUrl(url), data, config).error(httper.log).success(function (data, status) {
                if (status == 200) {
                    if (data.respCode) {
                        if (data.respCode == '1000') {
                            logger.logSuccess(data.msg);
                        } else {
                            logger.logError(data.msg);
                        }
                    } else {
                        logger.logSuccess(data.msg);
                    }
                } else {
                    logger.logError(data.msg);
                }
            })
        });
    };

    httper.postPut = function (url, data, config) {
        return httper.submit(function () {
            return $http.post(absUrl(url), data, config).error(httper.log).success(function (data, status) {
                if (!config || !config.hideLogger) {
                    httper.log(data, status, "修改成功");
                }
            })
        });
    };

    //手机号码校验
    httper.isPoneAvailable = function (phoneNo) {
        var myreg = /^[1][0-9]{10}$/;
        if (!myreg.test(phoneNo)) {
            return false;
        } else {
            return true;
        }
    };

    //18位数字或者字母
    httper.isId = function (id) {
        var myreg = /^[A-Za-z0-9]{18}$/;
        if (!myreg.test(id)) {
            return false;
        } else {
            return true;
        }
    };

    //仅数字校验
    httper.isNumber = function (number) {
        var myreg = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
        if (!myreg.test(number)) {
            return false;
        } else {
            return true;
        }
    };

    //仅空格校验
    httper.isBlank = function (str) {
        var myreg = /\s+/;
        if (myreg.test(str)) {
            return true;
        } else {
            return false;
        }
    };

    //仅空格校验
    httper.isBlankCenter = function (str) {
        var myreg = /^[\s　]|[ ]$/gi;
        if (myreg.test(str)) {
            return true;
        } else {
            return false;
        }
    };

    //仅校验是否为空
    httper.isNull = function (value) {
        if (angular.isUndefined(value) || value == null || value === "") {
            return true;
        }
        return false;
    };

    httper.put = function (url, data) {
        return httper.submit(function () {
            return $http.put(absUrl(url), data).error(httper.log).success(function (data, status) {
                httper.log(data, status, "修改成功")
            });
        });
    };

    httper.delete = function (url) {
        return $http.delete(absUrl(url)).error(httper.log).success(function (data, status) {
            httper.log(data, status, "删除成功")
        });
    };

    httper.download = function (url, params) {
        var url = absUrl(url)
        if (params) {
            url = url + "?" + jQuery.param(JSON.parse(JSON.stringify(params)));
        }
        window.open(url);
    }

    httper.submit = function (submitFunction) {
        if (!httper.lastSubmitTime) {
            httper.lastSubmitTime = new Date().getTime() - 1000;
        }
        var interval = new Date().getTime() - httper.lastSubmitTime;
        if (interval >= 1000) {
            httper.lastSubmitTime = new Date().getTime();
            httper.submitFuntion = submitFunction;
        } else {
            httper.lastSubmitTime = new Date().getTime();
            httper.submitFuntion = function () {
                logger.logWarning("请不要重复提交");
                var empty = {};
                empty.success = function (fn) {
                    return empty;
                };
                empty.error = function (fn) {
                    return empty;
                };
                return empty;
            };
        }
        return httper.submitFuntion();
    }
    return httper;

}]);
;

