module.exports = (function() {
var __MODS__ = {};
var __DEFINE__ = function(modId, func, req) { var m = { exports: {}, _tempexports: {} }; __MODS__[modId] = { status: 0, func: func, req: req, m: m }; };
var __REQUIRE__ = function(modId, source) { if(!__MODS__[modId]) return require(source); if(!__MODS__[modId].status) { var m = __MODS__[modId].m; m._exports = m._tempexports; var desp = Object.getOwnPropertyDescriptor(m, "exports"); if (desp && desp.configurable) Object.defineProperty(m, "exports", { set: function (val) { if(typeof val === "object" && val !== m._exports) { m._exports.__proto__ = val.__proto__; Object.keys(val).forEach(function (k) { m._exports[k] = val[k]; }); } m._tempexports = val }, get: function () { return m._tempexports; } }); __MODS__[modId].status = 1; __MODS__[modId].func(__MODS__[modId].req, m, m.exports); } return __MODS__[modId].m.exports; };
var __REQUIRE_WILDCARD__ = function(obj) { if(obj && obj.__esModule) { return obj; } else { var newObj = {}; if(obj != null) { for(var k in obj) { if (Object.prototype.hasOwnProperty.call(obj, k)) newObj[k] = obj[k]; } } newObj.default = obj; return newObj; } };
var __REQUIRE_DEFAULT__ = function(obj) { return obj && obj.__esModule ? obj.default : obj; };
__DEFINE__(1595572137173, function(require, module, exports) {


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = _default;

var t = _interopRequireWildcard(require("@babel/types"));

function _getRequireWildcardCache() { if (typeof WeakMap !== "function") return null; var cache = new WeakMap(); _getRequireWildcardCache = function () { return cache; }; return cache; }

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } if (obj === null || typeof obj !== "object" && typeof obj !== "function") { return { default: obj }; } var cache = _getRequireWildcardCache(); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj.default = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

function getObjRef(node, nodes, file, scope) {
  let ref;

  if (t.isSuper(node)) {
    return node;
  } else if (t.isIdentifier(node)) {
    if (scope.hasBinding(node.name)) {
      return node;
    } else {
      ref = node;
    }
  } else if (t.isMemberExpression(node)) {
    ref = node.object;

    if (t.isSuper(ref) || t.isIdentifier(ref) && scope.hasBinding(ref.name)) {
      return ref;
    }
  } else {
    throw new Error(`We can't explode this node type ${node.type}`);
  }

  const temp = scope.generateUidIdentifierBasedOnNode(ref);
  scope.push({
    id: temp
  });
  nodes.push(t.assignmentExpression("=", t.cloneNode(temp), t.cloneNode(ref)));
  return temp;
}

function getPropRef(node, nodes, file, scope) {
  const prop = node.property;
  const key = t.toComputedKey(node, prop);
  if (t.isLiteral(key) && t.isPureish(key)) return key;
  const temp = scope.generateUidIdentifierBasedOnNode(prop);
  scope.push({
    id: temp
  });
  nodes.push(t.assignmentExpression("=", t.cloneNode(temp), t.cloneNode(prop)));
  return temp;
}

function _default(node, nodes, file, scope, allowedSingleIdent) {
  let obj;

  if (t.isIdentifier(node) && allowedSingleIdent) {
    obj = node;
  } else {
    obj = getObjRef(node, nodes, file, scope);
  }

  let ref, uid;

  if (t.isIdentifier(node)) {
    ref = t.cloneNode(node);
    uid = obj;
  } else {
    const prop = getPropRef(node, nodes, file, scope);
    const computed = node.computed || t.isLiteral(prop);
    uid = t.memberExpression(t.cloneNode(obj), t.cloneNode(prop), computed);
    ref = t.memberExpression(t.cloneNode(obj), t.cloneNode(prop), computed);
  }

  return {
    uid: uid,
    ref: ref
  };
}
}, function(modId) {var map = {}; return __REQUIRE__(map[modId], modId); })
return __REQUIRE__(1595572137173);
})()
//# sourceMappingURL=index.js.map