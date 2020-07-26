module.exports = (function() {
var __MODS__ = {};
var __DEFINE__ = function(modId, func, req) { var m = { exports: {}, _tempexports: {} }; __MODS__[modId] = { status: 0, func: func, req: req, m: m }; };
var __REQUIRE__ = function(modId, source) { if(!__MODS__[modId]) return require(source); if(!__MODS__[modId].status) { var m = __MODS__[modId].m; m._exports = m._tempexports; var desp = Object.getOwnPropertyDescriptor(m, "exports"); if (desp && desp.configurable) Object.defineProperty(m, "exports", { set: function (val) { if(typeof val === "object" && val !== m._exports) { m._exports.__proto__ = val.__proto__; Object.keys(val).forEach(function (k) { m._exports[k] = val[k]; }); } m._tempexports = val }, get: function () { return m._tempexports; } }); __MODS__[modId].status = 1; __MODS__[modId].func(__MODS__[modId].req, m, m.exports); } return __MODS__[modId].m.exports; };
var __REQUIRE_WILDCARD__ = function(obj) { if(obj && obj.__esModule) { return obj; } else { var newObj = {}; if(obj != null) { for(var k in obj) { if (Object.prototype.hasOwnProperty.call(obj, k)) newObj[k] = obj[k]; } } newObj.default = obj; return newObj; } };
var __REQUIRE_DEFAULT__ = function(obj) { return obj && obj.__esModule ? obj.default : obj; };
__DEFINE__(1595572137155, function(require, module, exports) {


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.createClassFeaturePlugin = createClassFeaturePlugin;
Object.defineProperty(exports, "injectInitialization", {
  enumerable: true,
  get: function () {
    return _misc.injectInitialization;
  }
});
Object.defineProperty(exports, "FEATURES", {
  enumerable: true,
  get: function () {
    return _features.FEATURES;
  }
});

var _core = require("@babel/core");

var _helperFunctionName = _interopRequireDefault(require("@babel/helper-function-name"));

var _helperSplitExportDeclaration = _interopRequireDefault(require("@babel/helper-split-export-declaration"));

var _fields = require("./fields");

var _decorators = require("./decorators");

var _misc = require("./misc");

var _features = require("./features");

var _package = _interopRequireDefault(require("../package.json"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const version = _package.default.version.split(".").reduce((v, x) => v * 1e5 + +x, 0);

const versionKey = "@babel/plugin-class-features/version";

function createClassFeaturePlugin({
  name,
  feature,
  loose,
  manipulateOptions
}) {
  return {
    name,
    manipulateOptions,

    pre() {
      (0, _features.enableFeature)(this.file, feature, loose);

      if (!this.file.get(versionKey) || this.file.get(versionKey) < version) {
        this.file.set(versionKey, version);
      }
    },

    visitor: {
      Class(path, state) {
        if (this.file.get(versionKey) !== version) return;
        (0, _features.verifyUsedFeatures)(path, this.file);
        const loose = (0, _features.isLoose)(this.file, feature);
        let constructor;
        let isDecorated = (0, _decorators.hasOwnDecorators)(path.node);
        const props = [];
        const elements = [];
        const computedPaths = [];
        const privateNames = new Set();
        const body = path.get("body");

        for (const path of body.get("body")) {
          (0, _features.verifyUsedFeatures)(path, this.file);

          if (path.node.computed) {
            computedPaths.push(path);
          }

          if (path.isPrivate()) {
            const {
              name
            } = path.node.key.id;
            const getName = `get ${name}`;
            const setName = `set ${name}`;

            if (path.node.kind === "get") {
              if (privateNames.has(getName) || privateNames.has(name) && !privateNames.has(setName)) {
                throw path.buildCodeFrameError("Duplicate private field");
              }

              privateNames.add(getName).add(name);
            } else if (path.node.kind === "set") {
              if (privateNames.has(setName) || privateNames.has(name) && !privateNames.has(getName)) {
                throw path.buildCodeFrameError("Duplicate private field");
              }

              privateNames.add(setName).add(name);
            } else {
              if (privateNames.has(name) && !privateNames.has(getName) && !privateNames.has(setName) || privateNames.has(name) && (privateNames.has(getName) || privateNames.has(setName))) {
                throw path.buildCodeFrameError("Duplicate private field");
              }

              privateNames.add(name);
            }
          }

          if (path.isClassMethod({
            kind: "constructor"
          })) {
            constructor = path;
          } else {
            elements.push(path);

            if (path.isProperty() || path.isPrivate()) {
              props.push(path);
            }
          }

          if (!isDecorated) isDecorated = (0, _decorators.hasOwnDecorators)(path.node);
        }

        if (!props.length && !isDecorated) return;
        let ref;

        if (path.isClassExpression() || !path.node.id) {
          (0, _helperFunctionName.default)(path);
          ref = path.scope.generateUidIdentifier("class");
        } else {
          ref = _core.types.cloneNode(path.node.id);
        }

        const privateNamesMap = (0, _fields.buildPrivateNamesMap)(props);
        const privateNamesNodes = (0, _fields.buildPrivateNamesNodes)(privateNamesMap, loose, state);
        (0, _fields.transformPrivateNamesUsage)(ref, path, privateNamesMap, loose, state);
        let keysNodes, staticNodes, instanceNodes, wrapClass;

        if (isDecorated) {
          staticNodes = keysNodes = [];
          ({
            instanceNodes,
            wrapClass
          } = (0, _decorators.buildDecoratedClass)(ref, path, elements, this.file));
        } else {
          keysNodes = (0, _misc.extractComputedKeys)(ref, path, computedPaths, this.file);
          ({
            staticNodes,
            instanceNodes,
            wrapClass
          } = (0, _fields.buildFieldsInitNodes)(ref, path.node.superClass, props, privateNamesMap, state, loose));
        }

        if (instanceNodes.length > 0) {
          (0, _misc.injectInitialization)(path, constructor, instanceNodes, (referenceVisitor, state) => {
            if (isDecorated) return;

            for (const prop of props) {
              if (prop.node.static) continue;
              prop.traverse(referenceVisitor, state);
            }
          });
        }

        path = wrapClass(path);
        path.insertBefore([...privateNamesNodes, ...keysNodes]);
        path.insertAfter(staticNodes);
      },

      PrivateName(path) {
        if (this.file.get(versionKey) !== version) return;
        throw path.buildCodeFrameError(`Unknown PrivateName "${path}"`);
      },

      ExportDefaultDeclaration(path) {
        if (this.file.get(versionKey) !== version) return;
        const decl = path.get("declaration");

        if (decl.isClassDeclaration() && (0, _decorators.hasDecorators)(decl.node)) {
          if (decl.node.id) {
            (0, _helperSplitExportDeclaration.default)(path);
          } else {
            decl.node.type = "ClassExpression";
          }
        }
      }

    }
  };
}
}, function(modId) {var map = {"./fields":1595572137156,"./decorators":1595572137158,"./misc":1595572137159,"./features":1595572137160,"../package.json":1595572137161}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1595572137156, function(require, module, exports) {


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.buildPrivateNamesMap = buildPrivateNamesMap;
exports.buildPrivateNamesNodes = buildPrivateNamesNodes;
exports.transformPrivateNamesUsage = transformPrivateNamesUsage;
exports.buildFieldsInitNodes = buildFieldsInitNodes;

var _core = require("@babel/core");

var _helperReplaceSupers = _interopRequireWildcard(require("@babel/helper-replace-supers"));

var _helperMemberExpressionToFunctions = _interopRequireDefault(require("@babel/helper-member-expression-to-functions"));

var _helperOptimiseCallExpression = _interopRequireDefault(require("@babel/helper-optimise-call-expression"));

var ts = _interopRequireWildcard(require("./typescript"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _getRequireWildcardCache() { if (typeof WeakMap !== "function") return null; var cache = new WeakMap(); _getRequireWildcardCache = function () { return cache; }; return cache; }

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } if (obj === null || typeof obj !== "object" && typeof obj !== "function") { return { default: obj }; } var cache = _getRequireWildcardCache(); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj.default = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function buildPrivateNamesMap(props) {
  const privateNamesMap = new Map();

  for (const prop of props) {
    const isPrivate = prop.isPrivate();
    const isMethod = !prop.isProperty();
    const isInstance = !prop.node.static;

    if (isPrivate) {
      const {
        name
      } = prop.node.key.id;
      const update = privateNamesMap.has(name) ? privateNamesMap.get(name) : {
        id: prop.scope.generateUidIdentifier(name),
        static: !isInstance,
        method: isMethod
      };

      if (prop.node.kind === "get") {
        update.getId = prop.scope.generateUidIdentifier(`get_${name}`);
      } else if (prop.node.kind === "set") {
        update.setId = prop.scope.generateUidIdentifier(`set_${name}`);
      } else if (prop.node.kind === "method") {
        update.methodId = prop.scope.generateUidIdentifier(name);
      }

      privateNamesMap.set(name, update);
    }
  }

  return privateNamesMap;
}

function buildPrivateNamesNodes(privateNamesMap, loose, state) {
  const initNodes = [];

  for (const [name, value] of privateNamesMap) {
    const {
      static: isStatic,
      method: isMethod,
      getId,
      setId
    } = value;
    const isAccessor = getId || setId;

    const id = _core.types.cloneNode(value.id);

    if (loose) {
      initNodes.push(_core.template.statement.ast`
          var ${id} = ${state.addHelper("classPrivateFieldLooseKey")}("${name}")
        `);
    } else if (isMethod && !isStatic) {
      if (isAccessor) {
        initNodes.push(_core.template.statement.ast`var ${id} = new WeakMap();`);
      } else {
        initNodes.push(_core.template.statement.ast`var ${id} = new WeakSet();`);
      }
    } else if (!isStatic) {
      initNodes.push(_core.template.statement.ast`var ${id} = new WeakMap();`);
    }
  }

  return initNodes;
}

function privateNameVisitorFactory(visitor) {
  const privateNameVisitor = Object.assign({}, visitor, {
    Class(path) {
      const {
        privateNamesMap
      } = this;
      const body = path.get("body.body");
      const visiblePrivateNames = new Map(privateNamesMap);
      const redeclared = [];

      for (const prop of body) {
        if (!prop.isPrivate()) continue;
        const {
          name
        } = prop.node.key.id;
        visiblePrivateNames.delete(name);
        redeclared.push(name);
      }

      if (!redeclared.length) {
        return;
      }

      path.get("body").traverse(nestedVisitor, Object.assign({}, this, {
        redeclared
      }));
      path.traverse(privateNameVisitor, Object.assign({}, this, {
        privateNamesMap: visiblePrivateNames
      }));
      path.skipKey("body");
    }

  });

  const nestedVisitor = _core.traverse.visitors.merge([Object.assign({}, visitor), _helperReplaceSupers.environmentVisitor]);

  return privateNameVisitor;
}

const privateNameVisitor = privateNameVisitorFactory({
  PrivateName(path) {
    const {
      privateNamesMap,
      redeclared
    } = this;
    const {
      node,
      parentPath
    } = path;

    if (!parentPath.isMemberExpression({
      property: node
    }) && !parentPath.isOptionalMemberExpression({
      property: node
    })) {
      return;
    }

    const {
      name
    } = node.id;
    if (!privateNamesMap.has(name)) return;
    if (redeclared && redeclared.includes(name)) return;
    this.handle(parentPath);
  }

});
const privateInVisitor = privateNameVisitorFactory({
  BinaryExpression(path) {
    const {
      operator,
      left,
      right
    } = path.node;
    if (operator !== "in") return;
    if (!path.get("left").isPrivateName()) return;
    const {
      loose,
      privateNamesMap,
      redeclared
    } = this;
    const {
      name
    } = left.id;
    if (!privateNamesMap.has(name)) return;
    if (redeclared && redeclared.includes(name)) return;

    if (loose) {
      const {
        id
      } = privateNamesMap.get(name);
      path.replaceWith(_core.template.expression.ast`
        Object.prototype.hasOwnProperty.call(${right}, ${_core.types.cloneNode(id)})
      `);
      return;
    }

    const {
      id,
      static: isStatic
    } = privateNamesMap.get(name);

    if (isStatic) {
      path.replaceWith(_core.template.expression.ast`${right} === ${this.classRef}`);
      return;
    }

    path.replaceWith(_core.template.expression.ast`${_core.types.cloneNode(id)}.has(${right})`);
  }

});
const privateNameHandlerSpec = {
  memoise(member, count) {
    const {
      scope
    } = member;
    const {
      object
    } = member.node;
    const memo = scope.maybeGenerateMemoised(object);

    if (!memo) {
      return;
    }

    this.memoiser.set(object, memo, count);
  },

  receiver(member) {
    const {
      object
    } = member.node;

    if (this.memoiser.has(object)) {
      return _core.types.cloneNode(this.memoiser.get(object));
    }

    return _core.types.cloneNode(object);
  },

  get(member) {
    const {
      classRef,
      privateNamesMap,
      file
    } = this;
    const {
      name
    } = member.node.property.id;
    const {
      id,
      static: isStatic,
      method: isMethod,
      methodId,
      getId,
      setId
    } = privateNamesMap.get(name);
    const isAccessor = getId || setId;

    if (isStatic) {
      const helperName = isMethod && !isAccessor ? "classStaticPrivateMethodGet" : "classStaticPrivateFieldSpecGet";
      return _core.types.callExpression(file.addHelper(helperName), [this.receiver(member), _core.types.cloneNode(classRef), _core.types.cloneNode(id)]);
    }

    if (isMethod) {
      if (isAccessor) {
        return _core.types.callExpression(file.addHelper("classPrivateFieldGet"), [this.receiver(member), _core.types.cloneNode(id)]);
      }

      return _core.types.callExpression(file.addHelper("classPrivateMethodGet"), [this.receiver(member), _core.types.cloneNode(id), _core.types.cloneNode(methodId)]);
    }

    return _core.types.callExpression(file.addHelper("classPrivateFieldGet"), [this.receiver(member), _core.types.cloneNode(id)]);
  },

  boundGet(member) {
    this.memoise(member, 1);
    return _core.types.callExpression(_core.types.memberExpression(this.get(member), _core.types.identifier("bind")), [this.receiver(member)]);
  },

  set(member, value) {
    const {
      classRef,
      privateNamesMap,
      file
    } = this;
    const {
      name
    } = member.node.property.id;
    const {
      id,
      static: isStatic,
      method: isMethod,
      setId,
      getId
    } = privateNamesMap.get(name);
    const isAccessor = getId || setId;

    if (isStatic) {
      const helperName = isMethod && !isAccessor ? "classStaticPrivateMethodSet" : "classStaticPrivateFieldSpecSet";
      return _core.types.callExpression(file.addHelper(helperName), [this.receiver(member), _core.types.cloneNode(classRef), _core.types.cloneNode(id), value]);
    }

    if (isMethod) {
      if (setId) {
        return _core.types.callExpression(file.addHelper("classPrivateFieldSet"), [this.receiver(member), _core.types.cloneNode(id), value]);
      }

      return _core.types.callExpression(file.addHelper("classPrivateMethodSet"), []);
    }

    return _core.types.callExpression(file.addHelper("classPrivateFieldSet"), [this.receiver(member), _core.types.cloneNode(id), value]);
  },

  destructureSet(member) {
    const {
      privateNamesMap,
      file
    } = this;
    const {
      name
    } = member.node.property.id;
    const {
      id
    } = privateNamesMap.get(name);
    return _core.types.memberExpression(_core.types.callExpression(file.addHelper("classPrivateFieldDestructureSet"), [this.receiver(member), _core.types.cloneNode(id)]), _core.types.identifier("value"));
  },

  call(member, args) {
    this.memoise(member, 1);
    return (0, _helperOptimiseCallExpression.default)(this.get(member), this.receiver(member), args, false);
  },

  optionalCall(member, args) {
    this.memoise(member, 1);
    return (0, _helperOptimiseCallExpression.default)(this.get(member), this.receiver(member), args, true);
  }

};
const privateNameHandlerLoose = {
  get(member) {
    const {
      privateNamesMap,
      file
    } = this;
    const {
      object
    } = member.node;
    const {
      name
    } = member.node.property.id;
    return _core.template.expression`BASE(REF, PROP)[PROP]`({
      BASE: file.addHelper("classPrivateFieldLooseBase"),
      REF: _core.types.cloneNode(object),
      PROP: _core.types.cloneNode(privateNamesMap.get(name).id)
    });
  },

  boundGet(member) {
    return _core.types.callExpression(_core.types.memberExpression(this.get(member), _core.types.identifier("bind")), [_core.types.cloneNode(member.node.object)]);
  },

  simpleSet(member) {
    return this.get(member);
  },

  destructureSet(member) {
    return this.get(member);
  },

  call(member, args) {
    return _core.types.callExpression(this.get(member), args);
  },

  optionalCall(member, args) {
    return _core.types.optionalCallExpression(this.get(member), args, true);
  }

};

function transformPrivateNamesUsage(ref, path, privateNamesMap, loose, state) {
  if (!privateNamesMap.size) return;
  const body = path.get("body");
  const handler = loose ? privateNameHandlerLoose : privateNameHandlerSpec;
  (0, _helperMemberExpressionToFunctions.default)(body, privateNameVisitor, Object.assign({
    privateNamesMap,
    classRef: ref,
    file: state
  }, handler));
  body.traverse(privateInVisitor, {
    privateNamesMap,
    classRef: ref,
    file: state,
    loose
  });
}

function buildPrivateFieldInitLoose(ref, prop, privateNamesMap) {
  const {
    id
  } = privateNamesMap.get(prop.node.key.id.name);
  const value = prop.node.value || prop.scope.buildUndefinedNode();
  return _core.template.statement.ast`
    Object.defineProperty(${ref}, ${_core.types.cloneNode(id)}, {
      // configurable is false by default
      // enumerable is false by default
      writable: true,
      value: ${value}
    });
  `;
}

function buildPrivateInstanceFieldInitSpec(ref, prop, privateNamesMap) {
  const {
    id
  } = privateNamesMap.get(prop.node.key.id.name);
  const value = prop.node.value || prop.scope.buildUndefinedNode();
  return _core.template.statement.ast`${_core.types.cloneNode(id)}.set(${ref}, {
    // configurable is always false for private elements
    // enumerable is always false for private elements
    writable: true,
    value: ${value},
  })`;
}

function buildPrivateStaticFieldInitSpec(prop, privateNamesMap) {
  const privateName = privateNamesMap.get(prop.node.key.id.name);
  const {
    id,
    getId,
    setId,
    initAdded
  } = privateName;
  const isAccessor = getId || setId;
  if (!prop.isProperty() && (initAdded || !isAccessor)) return;

  if (isAccessor) {
    privateNamesMap.set(prop.node.key.id.name, Object.assign({}, privateName, {
      initAdded: true
    }));
    return _core.template.statement.ast`
      var ${_core.types.cloneNode(id)} = {
        // configurable is false by default
        // enumerable is false by default
        // writable is false by default
        get: ${getId ? getId.name : prop.scope.buildUndefinedNode()},
        set: ${setId ? setId.name : prop.scope.buildUndefinedNode()}
      }
    `;
  }

  const value = prop.node.value || prop.scope.buildUndefinedNode();
  return _core.template.statement.ast`
    var ${_core.types.cloneNode(id)} = {
      // configurable is false by default
      // enumerable is false by default
      writable: true,
      value: ${value}
    };
  `;
}

function buildPrivateMethodInitLoose(ref, prop, privateNamesMap) {
  const privateName = privateNamesMap.get(prop.node.key.id.name);
  const {
    methodId,
    id,
    getId,
    setId,
    initAdded
  } = privateName;
  if (initAdded) return;

  if (methodId) {
    return _core.template.statement.ast`
        Object.defineProperty(${ref}, ${id}, {
          // configurable is false by default
          // enumerable is false by default
          // writable is false by default
          value: ${methodId.name}
        });
      `;
  }

  const isAccessor = getId || setId;

  if (isAccessor) {
    privateNamesMap.set(prop.node.key.id.name, Object.assign({}, privateName, {
      initAdded: true
    }));
    return _core.template.statement.ast`
      Object.defineProperty(${ref}, ${id}, {
        // configurable is false by default
        // enumerable is false by default
        // writable is false by default
        get: ${getId ? getId.name : prop.scope.buildUndefinedNode()},
        set: ${setId ? setId.name : prop.scope.buildUndefinedNode()}
      });
    `;
  }
}

function buildPrivateInstanceMethodInitSpec(ref, prop, privateNamesMap) {
  const privateName = privateNamesMap.get(prop.node.key.id.name);
  const {
    id,
    getId,
    setId,
    initAdded
  } = privateName;
  if (initAdded) return;
  const isAccessor = getId || setId;

  if (isAccessor) {
    privateNamesMap.set(prop.node.key.id.name, Object.assign({}, privateName, {
      initAdded: true
    }));
    return _core.template.statement.ast`
      ${id}.set(${ref}, {
        get: ${getId ? getId.name : prop.scope.buildUndefinedNode()},
        set: ${setId ? setId.name : prop.scope.buildUndefinedNode()}
      });
    `;
  }

  return _core.template.statement.ast`${id}.add(${ref})`;
}

function buildPublicFieldInitLoose(ref, prop) {
  const {
    key,
    computed
  } = prop.node;
  const value = prop.node.value || prop.scope.buildUndefinedNode();
  return _core.types.expressionStatement(_core.types.assignmentExpression("=", _core.types.memberExpression(ref, key, computed || _core.types.isLiteral(key)), value));
}

function buildPublicFieldInitSpec(ref, prop, state) {
  const {
    key,
    computed
  } = prop.node;
  const value = prop.node.value || prop.scope.buildUndefinedNode();
  return _core.types.expressionStatement(_core.types.callExpression(state.addHelper("defineProperty"), [ref, computed || _core.types.isLiteral(key) ? key : _core.types.stringLiteral(key.name), value]));
}

function buildPrivateStaticMethodInitLoose(ref, prop, state, privateNamesMap) {
  const privateName = privateNamesMap.get(prop.node.key.id.name);
  const {
    id,
    methodId,
    getId,
    setId,
    initAdded
  } = privateName;
  if (initAdded) return;
  const isAccessor = getId || setId;

  if (isAccessor) {
    privateNamesMap.set(prop.node.key.id.name, Object.assign({}, privateName, {
      initAdded: true
    }));
    return _core.template.statement.ast`
      Object.defineProperty(${ref}, ${id}, {
        // configurable is false by default
        // enumerable is false by default
        // writable is false by default
        get: ${getId ? getId.name : prop.scope.buildUndefinedNode()},
        set: ${setId ? setId.name : prop.scope.buildUndefinedNode()}
      })
    `;
  }

  return _core.template.statement.ast`
    Object.defineProperty(${ref}, ${id}, {
      // configurable is false by default
      // enumerable is false by default
      // writable is false by default
      value: ${methodId.name}
    });
  `;
}

function buildPrivateMethodDeclaration(prop, privateNamesMap, loose = false) {
  const privateName = privateNamesMap.get(prop.node.key.id.name);
  const {
    id,
    methodId,
    getId,
    setId,
    getterDeclared,
    setterDeclared,
    static: isStatic
  } = privateName;
  const {
    params,
    body,
    generator,
    async
  } = prop.node;

  const methodValue = _core.types.functionExpression(methodId, params, body, generator, async);

  const isGetter = getId && !getterDeclared && params.length === 0;
  const isSetter = setId && !setterDeclared && params.length > 0;

  if (isGetter) {
    privateNamesMap.set(prop.node.key.id.name, Object.assign({}, privateName, {
      getterDeclared: true
    }));
    return _core.types.variableDeclaration("var", [_core.types.variableDeclarator(getId, methodValue)]);
  }

  if (isSetter) {
    privateNamesMap.set(prop.node.key.id.name, Object.assign({}, privateName, {
      setterDeclared: true
    }));
    return _core.types.variableDeclaration("var", [_core.types.variableDeclarator(setId, methodValue)]);
  }

  if (isStatic && !loose) {
    return _core.types.variableDeclaration("var", [_core.types.variableDeclarator(_core.types.cloneNode(id), _core.types.functionExpression(id, params, body, generator, async))]);
  }

  return _core.types.variableDeclaration("var", [_core.types.variableDeclarator(_core.types.cloneNode(methodId), methodValue)]);
}

const thisContextVisitor = _core.traverse.visitors.merge([{
  ThisExpression(path, state) {
    state.needsClassRef = true;
    path.replaceWith(_core.types.cloneNode(state.classRef));
  }

}, _helperReplaceSupers.environmentVisitor]);

function replaceThisContext(path, ref, superRef, file, loose) {
  const state = {
    classRef: ref,
    needsClassRef: false
  };
  const replacer = new _helperReplaceSupers.default({
    methodPath: path,
    isLoose: loose,
    superRef,
    file,

    getObjectRef() {
      state.needsClassRef = true;
      return path.node.static ? ref : _core.types.memberExpression(ref, _core.types.identifier("prototype"));
    }

  });
  replacer.replace();

  if (path.isProperty()) {
    path.traverse(thisContextVisitor, state);
  }

  return state.needsClassRef;
}

function buildFieldsInitNodes(ref, superRef, props, privateNamesMap, state, loose) {
  const staticNodes = [];
  const instanceNodes = [];
  let needsClassRef = false;

  for (const prop of props) {
    ts.assertFieldTransformed(prop);
    const isStatic = prop.node.static;
    const isInstance = !isStatic;
    const isPrivate = prop.isPrivate();
    const isPublic = !isPrivate;
    const isField = prop.isProperty();
    const isMethod = !isField;

    if (isStatic || isMethod && isPrivate) {
      const replaced = replaceThisContext(prop, ref, superRef, state, loose);
      needsClassRef = needsClassRef || replaced;
    }

    switch (true) {
      case isStatic && isPrivate && isField && loose:
        needsClassRef = true;
        staticNodes.push(buildPrivateFieldInitLoose(_core.types.cloneNode(ref), prop, privateNamesMap));
        break;

      case isStatic && isPrivate && isField && !loose:
        needsClassRef = true;
        staticNodes.push(buildPrivateStaticFieldInitSpec(prop, privateNamesMap));
        break;

      case isStatic && isPublic && isField && loose:
        needsClassRef = true;
        staticNodes.push(buildPublicFieldInitLoose(_core.types.cloneNode(ref), prop));
        break;

      case isStatic && isPublic && isField && !loose:
        needsClassRef = true;
        staticNodes.push(buildPublicFieldInitSpec(_core.types.cloneNode(ref), prop, state));
        break;

      case isInstance && isPrivate && isField && loose:
        instanceNodes.push(buildPrivateFieldInitLoose(_core.types.thisExpression(), prop, privateNamesMap));
        break;

      case isInstance && isPrivate && isField && !loose:
        instanceNodes.push(buildPrivateInstanceFieldInitSpec(_core.types.thisExpression(), prop, privateNamesMap));
        break;

      case isInstance && isPrivate && isMethod && loose:
        instanceNodes.unshift(buildPrivateMethodInitLoose(_core.types.thisExpression(), prop, privateNamesMap));
        staticNodes.push(buildPrivateMethodDeclaration(prop, privateNamesMap, loose));
        break;

      case isInstance && isPrivate && isMethod && !loose:
        instanceNodes.unshift(buildPrivateInstanceMethodInitSpec(_core.types.thisExpression(), prop, privateNamesMap));
        staticNodes.push(buildPrivateMethodDeclaration(prop, privateNamesMap, loose));
        break;

      case isStatic && isPrivate && isMethod && !loose:
        needsClassRef = true;
        staticNodes.push(buildPrivateStaticFieldInitSpec(prop, privateNamesMap));
        staticNodes.unshift(buildPrivateMethodDeclaration(prop, privateNamesMap, loose));
        break;

      case isStatic && isPrivate && isMethod && loose:
        needsClassRef = true;
        staticNodes.push(buildPrivateStaticMethodInitLoose(_core.types.cloneNode(ref), prop, state, privateNamesMap));
        staticNodes.unshift(buildPrivateMethodDeclaration(prop, privateNamesMap, loose));
        break;

      case isInstance && isPublic && isField && loose:
        instanceNodes.push(buildPublicFieldInitLoose(_core.types.thisExpression(), prop));
        break;

      case isInstance && isPublic && isField && !loose:
        instanceNodes.push(buildPublicFieldInitSpec(_core.types.thisExpression(), prop, state));
        break;

      default:
        throw new Error("Unreachable.");
    }
  }

  return {
    staticNodes: staticNodes.filter(Boolean),
    instanceNodes: instanceNodes.filter(Boolean),

    wrapClass(path) {
      for (const prop of props) {
        prop.remove();
      }

      if (!needsClassRef) return path;

      if (path.isClassExpression()) {
        path.scope.push({
          id: ref
        });
        path.replaceWith(_core.types.assignmentExpression("=", _core.types.cloneNode(ref), path.node));
      } else if (!path.node.id) {
        path.node.id = ref;
      }

      return path;
    }

  };
}
}, function(modId) { var map = {"./typescript":1595572137157}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1595572137157, function(require, module, exports) {


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.assertFieldTransformed = assertFieldTransformed;

function assertFieldTransformed(path) {
  if (path.node.declare) {
    throw path.buildCodeFrameError(`TypeScript 'declare' fields must first be transformed by ` + `@babel/plugin-transform-typescript.\n` + `If you have already enabled that plugin (or '@babel/preset-typescript'), make sure ` + `that it runs before any plugin related to additional class features:\n` + ` - @babel/plugin-proposal-class-properties\n` + ` - @babel/plugin-proposal-private-methods\n` + ` - @babel/plugin-proposal-decorators`);
  }
}
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1595572137158, function(require, module, exports) {


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.hasOwnDecorators = hasOwnDecorators;
exports.hasDecorators = hasDecorators;
exports.buildDecoratedClass = buildDecoratedClass;

var _core = require("@babel/core");

var _helperReplaceSupers = _interopRequireDefault(require("@babel/helper-replace-supers"));

var _helperFunctionName = _interopRequireDefault(require("@babel/helper-function-name"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function hasOwnDecorators(node) {
  return !!(node.decorators && node.decorators.length);
}

function hasDecorators(node) {
  return hasOwnDecorators(node) || node.body.body.some(hasOwnDecorators);
}

function prop(key, value) {
  if (!value) return null;
  return _core.types.objectProperty(_core.types.identifier(key), value);
}

function method(key, body) {
  return _core.types.objectMethod("method", _core.types.identifier(key), [], _core.types.blockStatement(body));
}

function takeDecorators(node) {
  let result;

  if (node.decorators && node.decorators.length > 0) {
    result = _core.types.arrayExpression(node.decorators.map(decorator => decorator.expression));
  }

  node.decorators = undefined;
  return result;
}

function getKey(node) {
  if (node.computed) {
    return node.key;
  } else if (_core.types.isIdentifier(node.key)) {
    return _core.types.stringLiteral(node.key.name);
  } else {
    return _core.types.stringLiteral(String(node.key.value));
  }
}

function extractElementDescriptor(classRef, superRef, path) {
  const {
    node,
    scope
  } = path;
  const isMethod = path.isClassMethod();

  if (path.isPrivate()) {
    throw path.buildCodeFrameError(`Private ${isMethod ? "methods" : "fields"} in decorated classes are not supported yet.`);
  }

  new _helperReplaceSupers.default({
    methodPath: path,
    methodNode: node,
    objectRef: classRef,
    isStatic: node.static,
    superRef,
    scope,
    file: this
  }, true).replace();
  const properties = [prop("kind", _core.types.stringLiteral(isMethod ? node.kind : "field")), prop("decorators", takeDecorators(node)), prop("static", node.static && _core.types.booleanLiteral(true)), prop("key", getKey(node))].filter(Boolean);

  if (isMethod) {
    const id = node.computed ? null : node.key;

    _core.types.toExpression(node);

    properties.push(prop("value", (0, _helperFunctionName.default)({
      node,
      id,
      scope
    }) || node));
  } else if (node.value) {
    properties.push(method("value", _core.template.statements.ast`return ${node.value}`));
  } else {
    properties.push(prop("value", scope.buildUndefinedNode()));
  }

  path.remove();
  return _core.types.objectExpression(properties);
}

function addDecorateHelper(file) {
  try {
    return file.addHelper("decorate");
  } catch (err) {
    if (err.code === "BABEL_HELPER_UNKNOWN") {
      err.message += "\n  '@babel/plugin-transform-decorators' in non-legacy mode" + " requires '@babel/core' version ^7.0.2 and you appear to be using" + " an older version.";
    }

    throw err;
  }
}

function buildDecoratedClass(ref, path, elements, file) {
  const {
    node,
    scope
  } = path;
  const initializeId = scope.generateUidIdentifier("initialize");
  const isDeclaration = node.id && path.isDeclaration();
  const isStrict = path.isInStrictMode();
  const {
    superClass
  } = node;
  node.type = "ClassDeclaration";
  if (!node.id) node.id = _core.types.cloneNode(ref);
  let superId;

  if (superClass) {
    superId = scope.generateUidIdentifierBasedOnNode(node.superClass, "super");
    node.superClass = superId;
  }

  const classDecorators = takeDecorators(node);

  const definitions = _core.types.arrayExpression(elements.filter(element => !element.node.abstract).map(extractElementDescriptor.bind(file, node.id, superId)));

  let replacement = _core.template.expression.ast`
    ${addDecorateHelper(file)}(
      ${classDecorators || _core.types.nullLiteral()},
      function (${initializeId}, ${superClass ? _core.types.cloneNode(superId) : null}) {
        ${node}
        return { F: ${_core.types.cloneNode(node.id)}, d: ${definitions} };
      },
      ${superClass}
    )
  `;
  let classPathDesc = "arguments.1.body.body.0";

  if (!isStrict) {
    replacement.arguments[1].body.directives.push(_core.types.directive(_core.types.directiveLiteral("use strict")));
  }

  if (isDeclaration) {
    replacement = _core.template.ast`let ${ref} = ${replacement}`;
    classPathDesc = "declarations.0.init." + classPathDesc;
  }

  return {
    instanceNodes: [_core.template.statement.ast`${_core.types.cloneNode(initializeId)}(this)`],

    wrapClass(path) {
      path.replaceWith(replacement);
      return path.get(classPathDesc);
    }

  };
}
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1595572137159, function(require, module, exports) {


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.injectInitialization = injectInitialization;
exports.extractComputedKeys = extractComputedKeys;

var _core = require("@babel/core");

var _helperReplaceSupers = require("@babel/helper-replace-supers");

const findBareSupers = _core.traverse.visitors.merge([{
  Super(path) {
    const {
      node,
      parentPath
    } = path;

    if (parentPath.isCallExpression({
      callee: node
    })) {
      this.push(parentPath);
    }
  }

}, _helperReplaceSupers.environmentVisitor]);

const referenceVisitor = {
  "TSTypeAnnotation|TypeAnnotation"(path) {
    path.skip();
  },

  ReferencedIdentifier(path) {
    if (this.scope.hasOwnBinding(path.node.name)) {
      this.scope.rename(path.node.name);
      path.skip();
    }
  }

};

function handleClassTDZ(path, state) {
  if (state.classBinding && state.classBinding === path.scope.getBinding(path.node.name)) {
    const classNameTDZError = state.file.addHelper("classNameTDZError");

    const throwNode = _core.types.callExpression(classNameTDZError, [_core.types.stringLiteral(path.node.name)]);

    path.replaceWith(_core.types.sequenceExpression([throwNode, path.node]));
    path.skip();
  }
}

const classFieldDefinitionEvaluationTDZVisitor = {
  ReferencedIdentifier: handleClassTDZ
};

function injectInitialization(path, constructor, nodes, renamer) {
  if (!nodes.length) return;
  const isDerived = !!path.node.superClass;

  if (!constructor) {
    const newConstructor = _core.types.classMethod("constructor", _core.types.identifier("constructor"), [], _core.types.blockStatement([]));

    if (isDerived) {
      newConstructor.params = [_core.types.restElement(_core.types.identifier("args"))];
      newConstructor.body.body.push(_core.template.statement.ast`super(...args)`);
    }

    [constructor] = path.get("body").unshiftContainer("body", newConstructor);
  }

  if (renamer) {
    renamer(referenceVisitor, {
      scope: constructor.scope
    });
  }

  if (isDerived) {
    const bareSupers = [];
    constructor.traverse(findBareSupers, bareSupers);
    let isFirst = true;

    for (const bareSuper of bareSupers) {
      if (isFirst) {
        bareSuper.insertAfter(nodes);
        isFirst = false;
      } else {
        bareSuper.insertAfter(nodes.map(n => _core.types.cloneNode(n)));
      }
    }
  } else {
    constructor.get("body").unshiftContainer("body", nodes);
  }
}

function extractComputedKeys(ref, path, computedPaths, file) {
  const declarations = [];
  const state = {
    classBinding: path.node.id && path.scope.getBinding(path.node.id.name),
    file
  };

  for (const computedPath of computedPaths) {
    const computedKey = computedPath.get("key");

    if (computedKey.isReferencedIdentifier()) {
      handleClassTDZ(computedKey, state);
    } else {
      computedKey.traverse(classFieldDefinitionEvaluationTDZVisitor, state);
    }

    const computedNode = computedPath.node;

    if (!computedKey.isConstantExpression()) {
      const ident = path.scope.generateUidIdentifierBasedOnNode(computedNode.key);
      path.scope.push({
        id: ident,
        kind: "let"
      });
      declarations.push(_core.types.expressionStatement(_core.types.assignmentExpression("=", _core.types.cloneNode(ident), computedNode.key)));
      computedNode.key = _core.types.cloneNode(ident);
    }
  }

  return declarations;
}
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1595572137160, function(require, module, exports) {


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.enableFeature = enableFeature;
exports.isLoose = isLoose;
exports.verifyUsedFeatures = verifyUsedFeatures;
exports.FEATURES = void 0;

var _decorators = require("./decorators");

const FEATURES = Object.freeze({
  fields: 1 << 1,
  privateMethods: 1 << 2,
  decorators: 1 << 3,
  privateIn: 1 << 4
});
exports.FEATURES = FEATURES;
const featuresSameLoose = new Map([[FEATURES.fields, "@babel/plugin-proposal-class-properties"], [FEATURES.privateMethods, "@babel/plugin-proposal-private-methods"], [FEATURES.privateIn, "@babel/plugin-proposal-private-private-property-in-object"]]);
const featuresKey = "@babel/plugin-class-features/featuresKey";
const looseKey = "@babel/plugin-class-features/looseKey";
const looseLowPriorityKey = "@babel/plugin-class-features/looseLowPriorityKey/#__internal__@babel/preset-env__please-overwrite-loose-instead-of-throwing";

function enableFeature(file, feature, loose) {
  if (!hasFeature(file, feature) || canIgnoreLoose(file, feature)) {
    file.set(featuresKey, file.get(featuresKey) | feature);

    if (loose === "#__internal__@babel/preset-env__prefer-true-but-false-is-ok-if-it-prevents-an-error") {
      setLoose(file, feature, true);
      file.set(looseLowPriorityKey, file.get(looseLowPriorityKey) | feature);
    } else if (loose === "#__internal__@babel/preset-env__prefer-false-but-true-is-ok-if-it-prevents-an-error") {
      setLoose(file, feature, false);
      file.set(looseLowPriorityKey, file.get(looseLowPriorityKey) | feature);
    } else {
      setLoose(file, feature, loose);
    }
  }

  let resolvedLoose;
  let higherPriorityPluginName;

  for (const [mask, name] of featuresSameLoose) {
    if (!hasFeature(file, mask)) continue;
    const loose = isLoose(file, mask);

    if (canIgnoreLoose(file, mask)) {
      continue;
    } else if (resolvedLoose === !loose) {
      throw new Error("'loose' mode configuration must be the same for @babel/plugin-proposal-class-properties, " + "@babel/plugin-proposal-private-methods and " + "@babel/plugin-proposal-private-property-in-object (when they are enabled).");
    } else {
      resolvedLoose = loose;
      higherPriorityPluginName = name;
    }
  }

  if (resolvedLoose !== undefined) {
    for (const [mask, name] of featuresSameLoose) {
      if (hasFeature(file, mask) && isLoose(file, mask) !== resolvedLoose) {
        setLoose(file, mask, resolvedLoose);
        console.warn(`Though the "loose" option was set to "${!resolvedLoose}" in your @babel/preset-env ` + `config, it will not be used for ${name} since the "loose" mode option was set to ` + `"${resolvedLoose}" for ${higherPriorityPluginName}.\nThe "loose" option must be the ` + `same for @babel/plugin-proposal-class-properties, @babel/plugin-proposal-private-methods ` + `and @babel/plugin-proposal-private-property-in-object (when they are enabled): you can ` + `silence this warning by explicitly adding\n` + `\t["${name}", { "loose": ${resolvedLoose} }]\n` + `to the "plugins" section of your Babel config.`);
      }
    }
  }
}

function hasFeature(file, feature) {
  return !!(file.get(featuresKey) & feature);
}

function isLoose(file, feature) {
  return !!(file.get(looseKey) & feature);
}

function setLoose(file, feature, loose) {
  if (loose) file.set(looseKey, file.get(looseKey) | feature);else file.set(looseKey, file.get(looseKey) & ~feature);
  file.set(looseLowPriorityKey, file.get(looseLowPriorityKey) & ~feature);
}

function canIgnoreLoose(file, feature) {
  return !!(file.get(looseLowPriorityKey) & feature);
}

function verifyUsedFeatures(path, file) {
  if ((0, _decorators.hasOwnDecorators)(path.node)) {
    if (!hasFeature(file, FEATURES.decorators)) {
      throw path.buildCodeFrameError("Decorators are not enabled." + "\nIf you are using " + '["@babel/plugin-proposal-decorators", { "legacy": true }], ' + 'make sure it comes *before* "@babel/plugin-proposal-class-properties" ' + "and enable loose mode, like so:\n" + '\t["@babel/plugin-proposal-decorators", { "legacy": true }]\n' + '\t["@babel/plugin-proposal-class-properties", { "loose": true }]');
    }

    if (path.isPrivate()) {
      throw path.buildCodeFrameError(`Private ${path.isClassMethod() ? "methods" : "fields"} in decorated classes are not supported yet.`);
    }
  }

  if (path.isPrivate() && path.isMethod()) {
    if (!hasFeature(file, FEATURES.privateMethods)) {
      throw path.buildCodeFrameError("Class private methods are not enabled.");
    }
  }

  if (path.isPrivateName() && path.parentPath.isBinaryExpression({
    operator: "in",
    left: path.node
  })) {
    if (!hasFeature(file, FEATURES.privateIn)) {
      throw path.buildCodeFrameError("Private property in checks are not enabled.");
    }
  }

  if (path.isProperty()) {
    if (!hasFeature(file, FEATURES.fields)) {
      throw path.buildCodeFrameError("Class fields are not enabled.");
    }
  }
}
}, function(modId) { var map = {"./decorators":1595572137158}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1595572137161, function(require, module, exports) {
module.exports = {
  "_from": "@babel/helper-create-class-features-plugin@^7.10.4",
  "_id": "@babel/helper-create-class-features-plugin@7.10.5",
  "_inBundle": false,
  "_integrity": "sha512-0nkdeijB7VlZoLT3r/mY3bUkw3T8WG/hNw+FATs/6+pG2039IJWjTYL0VTISqsNHMUTEnwbVnc89WIJX9Qed0A==",
  "_location": "/@babel/helper-create-class-features-plugin",
  "_phantomChildren": {},
  "_requested": {
    "type": "range",
    "registry": true,
    "raw": "@babel/helper-create-class-features-plugin@^7.10.4",
    "name": "@babel/helper-create-class-features-plugin",
    "escapedName": "@babel%2fhelper-create-class-features-plugin",
    "scope": "@babel",
    "rawSpec": "^7.10.4",
    "saveSpec": null,
    "fetchSpec": "^7.10.4"
  },
  "_requiredBy": [
    "/@babel/plugin-proposal-class-properties",
    "/@babel/plugin-proposal-private-methods"
  ],
  "_resolved": "https://registry.npmjs.org/@babel/helper-create-class-features-plugin/-/helper-create-class-features-plugin-7.10.5.tgz",
  "_shasum": "9f61446ba80e8240b0a5c85c6fdac8459d6f259d",
  "_spec": "@babel/helper-create-class-features-plugin@^7.10.4",
  "_where": "C:\\Users\\peach\\github\\wechatAppUI\\node_modules\\@babel\\plugin-proposal-class-properties",
  "author": {
    "name": "The Babel Team",
    "url": "https://babeljs.io/team"
  },
  "bugs": {
    "url": "https://github.com/babel/babel/issues"
  },
  "bundleDependencies": false,
  "dependencies": {
    "@babel/helper-function-name": "^7.10.4",
    "@babel/helper-member-expression-to-functions": "^7.10.5",
    "@babel/helper-optimise-call-expression": "^7.10.4",
    "@babel/helper-plugin-utils": "^7.10.4",
    "@babel/helper-replace-supers": "^7.10.4",
    "@babel/helper-split-export-declaration": "^7.10.4"
  },
  "deprecated": false,
  "description": "Compile class public and private fields, private methods and decorators to ES6",
  "devDependencies": {
    "@babel/core": "^7.10.5",
    "@babel/helper-plugin-test-runner": "^7.10.4"
  },
  "gitHead": "f7964a9ac51356f7df6404a25b27ba1cffba1ba7",
  "homepage": "https://github.com/babel/babel#readme",
  "keywords": [
    "babel",
    "babel-plugin"
  ],
  "license": "MIT",
  "main": "lib/index.js",
  "name": "@babel/helper-create-class-features-plugin",
  "peerDependencies": {
    "@babel/core": "^7.0.0"
  },
  "publishConfig": {
    "access": "public"
  },
  "repository": {
    "type": "git",
    "url": "git+https://github.com/babel/babel.git",
    "directory": "packages/babel-helper-create-class-features-plugin"
  },
  "version": "7.10.5"
}

}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
return __REQUIRE__(1595572137155);
})()
//# sourceMappingURL=index.js.map