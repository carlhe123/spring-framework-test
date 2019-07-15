var app = angular.module('demo', []);

app.controller('demoController', function($scope, $timeout) {
    var apple_selected, tree, treedata_avm,for_each_branch,collapse_all_tree;
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    $scope.my_tree_handler = function(branch) {
        var _ref;
        $scope.output = "You selected: " + branch.label;
        if ((_ref = branch.data) != null ? _ref.description : void 0) {
            return $scope.output += '(' + branch.data.description + ')';
        }
    };
    $scope.addProdGroup = function(branch){
        alert(branch.label);
    };
    $scope.deleteProdGroup = function(branch){
        alert(branch.label);
    };

    apple_selected = function(branch) {
        return $scope.output = "APPLE! : " + branch.label;
    };
    treedata_avm=[
        {
            name: 'Animal',
            id: 1,
            level: 1,
            parentId: -1,
            children: [
                {
                    name: 'Dog',
                    id: 2,
                    level: 2,
                    parentId: 1,
                    data: {
                        description: "man's best friend"
                    }
                }, {
                    name: 'Cat',
                    id: 3,
                    level: 2,
                    parentId: 1,
                    data: {
                        description: "Felis catus"
                    }
                }, {
                    name: 'Hippopotamus',
                    id: 4,
                    level: 2,
                    parentId: 1,
                    data: {
                        description: "hungry, hungry"
                    }
                }, {
                    name: 'Chicken',
                    id: 5,
                    parentId: 1,
                    level: 2,
                    children: [{
                        name: 'White Leghorn',
                        parentId: 5,
                        level: 3, id: 6
                    }, {
                        name: 'Rhode Island Red',
                        parentId: 5,
                        level: 3, id: 7
                    }, {
                        name: 'Jersey Giant',
                        level: 3,
                        parentId: 5,
                        id: 8
                    }]
                }
            ]
        },
        {
            name: 'Vegetable',
            id: 9,
            level: 1,
            parentId: -1,
            data: {
                definition: "A plant or part of a plant used as food, typically as accompaniment to meat or fish, such as a cabbage, potato, carrot, or bean.",
                data_can_contain_anything: true
            },
            children: [
                {
                    name: 'Oranges',
                    level: 2,
                    parentId: 9,
                    id: 10
                },
                {
                    name: 'Apples',
                    id: 11,
                    parentId: 10,
                    level: 2,
                    children: [
                        {
                            name: 'Granny Smith',
                            id: 12,
                            parentId: 11,
                            level: 3,
                        }, {
                            name: 'Red Delicous',
                            id: 13,
                            parentId: 11,
                            level: 3,
                        }, {
                            name: 'Fuji',
                            id: 14,
                            parentId: 11,
                            level: 3,
                        }
                    ]
                }
            ]
        }, {
            name: 'Mineral',
            id: 15,
            level: 1,
            parentId: -1,
            children: [
                {
                    name: 'Rock',
                    id: 16,
                    parentId: 15,
                    level: 2,
                    children: [
                        {
                            name: 'Igneous',
                            level: 3,
                            parentId: 16,
                            id: 17
                        },
                        {
                            name: 'Sedimentary',
                            level: 3,
                            parentId: 16,
                            id: 18
                        },
                        {
                            name: 'Metamorphic',
                            parentId: 16,
                            level: 3, id: 19
                        }]
                }, {
                    name: 'Metal',
                    id: 20,
                    parentId: 15,
                    level: 2,
                    children: [{
                        name: 'Aluminum',
                        parentId: 20,
                        level: 3, id: 21
                    }, {
                        name: 'Steel',
                        parentId: 20,
                        level: 3, id: 22
                    }, {
                        name: 'Copper',
                        parentId: 20,
                        level: 3, id: 23
                    }]
                }, {
                    name: 'Plastic',
                    id: 24,
                    parentId: 15,
                    level: 2,
                    children: [
                        {
                            name: 'Thermoplastic',
                            id: 25,
                            parentId: 24,
                            level: 3,
                            children: [{
                                name: 'polyethylene',
                                parentId: 25,
                                level: 4, id: 26
                            }, {
                                name: 'polypropylene',
                                level: 4,
                                parentId: 25,
                                id: 27
                            }, {
                                name: 'polystyrene',
                                parentId: 25,
                                level: 4, id: 28
                            },
                                {
                                    name: ' polyvinyl chloride',
                                    parentId: 25,
                                    level: 4, id: 29
                                }]
                        }, {
                            name: 'Thermosetting Polymer',
                            id: 30,
                            parentId: 24,
                            level: 3,
                            children: [
                                {
                                    name: 'polyester',
                                    parentId: 30,
                                    level: 4, id: 31
                                },
                                {
                                    name: 'polyurethane',
                                    parentId: 30,
                                    level: 4,
                                    id: 32
                                }, {
                                    name: 'vulcanized rubber',
                                    parentId: 30,
                                    level: 4, id: 33
                                }, {
                                    name: 'bakelite',
                                    parentId: 30,
                                    level: 4,
                                    id: 34
                                }, {
                                    name: 'urea-formaldehyde',
                                    parentId: 30,
                                    level: 4, id: 35
                                }]
                        }
                    ]
                }
            ]
        }
    ];

    $scope.treeData = treedata_avm;
    $scope.my_tree = tree =  {};
});
