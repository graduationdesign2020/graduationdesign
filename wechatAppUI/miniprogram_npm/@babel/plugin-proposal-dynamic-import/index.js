module.exports = (function() {
var __MODS__ = {};
var __DEFINE__ = function(modId, func, req) { var m = { exports: {}, _tempexports: {} }; __MODS__[modId] = { status: 0, func: func, req: req, m: m }; };
var __REQUIRE__ = function(modId, source) { if(!__MODS__[modId]) return require(source); if(!__MODS__[modId].status) { var m = __MODS__[modId].m; m._exports = m._tempexports; var desp = Object.getOwnPropertyDescriptor(m, "exports"); if (desp && desp.configurable) Object.defineProperty(m, "exports", { set: function (val) { if(typeof val === "object" && val !== m._exports) { m._exports.__proto__ = val.__proto__; Object.keys(val).forEach(function (k) { m._exports[k] = val[k]; }); } m._tempexports = val }, get: function () { return m._tempexports; } }); __MODS__[modId].status = 1; __MODS__[modId].func(__MODS__[modId].req, m, m.exports); } return __MODS__[modId].m.exports; };
var __REQUIRE_WILDCARD__ = function(obj) { if(obj && obj.__esModule) { return obj; } else { var newObj = {}; if(obj != null) { for(var k in obj) { if (Object.prototype.hasOwnProperty.call(obj, k)) newObj[k] = obj[k]; } } newObj.default = obj; return newObj; } };
var __REQUIRE_DEFAULT__ = function(obj) { return obj && obj.__esModule ? obj.default : obj; };
__DEFINE__(1595572137203, function(require, module, exports) {


Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;

var _helperPluginUtils = require("@babel/helper-plugin-utils");

var _pluginSyntaxDynamicImport = _interopRequireDefault(require("@babel/plugin-syntax-dynamic-import"));

var _package = require("../package.json");

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

const SUPPORTED_MODULES = ["commonjs", "amd", "systemjs"];
const MODULES_NOT_FOUND = `\
@babel/plugin-proposal-dynamic-import depends on a modules
transform plugin. Supported plugins are:
 - @babel/plugin-transform-modules-commonjs ^7.4.0
 - @babel/plugin-transform-modules-amd ^7.4.0
 - @babel/plugin-transform-modules-systemjs ^7.4.0

If you are using Webpack or Rollup and thus don't want
Babel to transpile your imports and exports, you can use
the @babel/plugin-syntax-dynamic-import plugin and let your
bundler handle dynamic imports.
`;

var _default = (0, _helperPluginUtils.declare)(api => {
  api.assertVersion(7);
  return {
    name: "proposal-dynamic-import",
    inherits: _pluginSyntaxDynamicImport.default,

    pre() {
      this.file.set("@babel/plugin-proposal-dynamic-import", _package.version);
    },

    visitor: {
      Program() {
        const modules = this.file.get("@babel/plugin-transform-modules-*");

        if (!SUPPORTED_MODULES.includes(modules)) {
          throw new Error(MODULES_NOT_FOUND);
        }
      }

    }
  };
});

exports.default = _default;
}, function(modId) {var map = {"../package.json":1595572137204}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1595572137204, function(require, module, exports) {
module.exports = {
  "_from": "@babel/plugin-proposal-dynamic-import@^7.10.4",
  "_id": "@babel/plugin-proposal-dynamic-import@7.10.4",
  "_inBundle": false,
  "_integrity": "sha512-up6oID1LeidOOASNXgv/CFbgBqTuKJ0cJjz6An5tWD+NVBNlp3VNSBxv2ZdU7SYl3NxJC7agAQDApZusV6uFwQ==",
  "_location": "/@babel/plugin-proposal-dynamic-import",
  "_phantomChildren": {},
  "_requested": {
    "type": "range",
    "registry": true,
    "raw": "@babel/plugin-proposal-dynamic-import@^7.10.4",
    "name": "@babel/plugin-proposal-dynamic-import",
    "escapedName": "@babel%2fplugin-proposal-dynamic-import",
    "scope": "@babel",
    "rawSpec": "^7.10.4",
    "saveSpec": null,
    "fetchSpec": "^7.10.4"
  },
  "_requiredBy": [
    "/@babel/preset-env"
  ],
  "_resolved": "https://registry.npmjs.org/@babel/plugin-proposal-dynamic-import/-/plugin-proposal-dynamic-import-7.10.4.tgz",
  "_shasum": "ba57a26cb98b37741e9d5bca1b8b0ddf8291f17e",
  "_spec": "@babel/plugin-proposal-dynamic-import@^7.10.4",
  "_where": "C:\\Users\\peach\\github\\wechatAppUI\\node_modules\\@babel\\preset-env",
  "bugs": {
    "url": "https://github.com/babel/babel/issues"
  },
  "bundleDependencies": false,
  "dependencies": {
    "@babel/helper-plugin-utils": "^7.10.4",
    "@babel/plugin-syntax-dynamic-import": "^7.8.0"
  },
  "deprecated": false,
  "description": "Transform import() expressions",
  "devDependencies": {
    "@babel/core": "^7.10.4",
    "@babel/helper-plugin-test-runner": "^7.10.4"
  },
  "gitHead": "7fd40d86a0d03ff0e9c3ea16b29689945433d4df",
  "homepage": "https://github.com/babel/babel#readme",
  "keywords": [
    "babel-plugin"
  ],
  "license": "MIT",
  "main": "lib/index.js",
  "name": "@babel/plugin-proposal-dynamic-import",
  "peerDependencies": {
    "@babel/core": "^7.0.0-0"
  },
  "publishConfig": {
    "access": "public"
  },
  "repository": {
    "type": "git",
    "url": "git+https://github.com/babel/babel.git",
    "directory": "packages/babel-plugin-proposal-dynamic-import"
  },
  "version": "7.10.4"
}

}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
return __REQUIRE__(1595572137203);
})()
//# sourceMappingURL=index.js.map