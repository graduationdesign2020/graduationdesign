module.exports = (function() {
var __MODS__ = {};
var __DEFINE__ = function(modId, func, req) { var m = { exports: {}, _tempexports: {} }; __MODS__[modId] = { status: 0, func: func, req: req, m: m }; };
var __REQUIRE__ = function(modId, source) { if(!__MODS__[modId]) return require(source); if(!__MODS__[modId].status) { var m = __MODS__[modId].m; m._exports = m._tempexports; var desp = Object.getOwnPropertyDescriptor(m, "exports"); if (desp && desp.configurable) Object.defineProperty(m, "exports", { set: function (val) { if(typeof val === "object" && val !== m._exports) { m._exports.__proto__ = val.__proto__; Object.keys(val).forEach(function (k) { m._exports[k] = val[k]; }); } m._tempexports = val }, get: function () { return m._tempexports; } }); __MODS__[modId].status = 1; __MODS__[modId].func(__MODS__[modId].req, m, m.exports); } return __MODS__[modId].m.exports; };
var __REQUIRE_WILDCARD__ = function(obj) { if(obj && obj.__esModule) { return obj; } else { var newObj = {}; if(obj != null) { for(var k in obj) { if (Object.prototype.hasOwnProperty.call(obj, k)) newObj[k] = obj[k]; } } newObj.default = obj; return newObj; } };
var __REQUIRE_DEFAULT__ = function(obj) { return obj && obj.__esModule ? obj.default : obj; };
__DEFINE__(1595572138975, function(require, module, exports) {
var _typeof=typeof Symbol==='function'&&typeof Symbol.iterator==='symbol'?function(obj){return typeof obj}:function(obj){return obj&&typeof Symbol==='function'&&obj.constructor===Symbol&&obj!==Symbol.prototype?'symbol':typeof obj};var http=require('http');var https=require('https');var url=require('url');var qs=require('querystring');var zlib=require('zlib');var util=require('util');var phin=function phin(opts,cb){if(typeof opts!=='string'){if(!opts.hasOwnProperty('url')){throw new Error('Missing url option from options for request method.')}}var addr=(typeof opts==='undefined'?'undefined':_typeof(opts))==='object'?url.parse(opts.url):url.parse(opts);var options={'hostname':addr.hostname,'port':addr.port||(addr.protocol.toLowerCase()==='http:'?80:443),'path':addr.path,'method':'GET','headers':{},'auth':addr.auth||null,'parse':'none','stream':false};if((typeof opts==='undefined'?'undefined':_typeof(opts))==='object'){options=Object.assign(options,opts)}options.port=Number(options.port);if(options.hasOwnProperty('timeout'))delete options.timeout;if(options.compressed===true){options.headers['accept-encoding']='gzip, deflate'}if(opts.hasOwnProperty('form')){if(_typeof(opts.form)!=='object'){throw new Error('phin \'form\' option must be of type Object if present.')}var formDataString=qs.stringify(opts.form);options.headers['Content-Type']='application/x-www-form-urlencoded';options.headers['Content-Length']=Buffer.byteLength(formDataString);opts.data=formDataString}var req=void 0;var resHandler=function resHandler(res){var stream=res;if(options.compressed===true){if(res.headers['content-encoding']==='gzip'){stream=res.pipe(zlib.createGunzip())}else if(res.headers['content-encoding']==='deflate'){stream=res.pipe(zlib.createInflate())}}if(options.stream===true){res.stream=stream;if(cb)cb(null,res)}else{res.body=new Buffer([]);stream.on('data',function(chunk){res.body=Buffer.concat([res.body,chunk])});stream.on('end',function(){if(cb){if(options.parse==='json'){try{res.body=JSON.parse(res.body.toString())}catch(err){cb('Invalid JSON received.',res);return}}cb(null,res)}})}};switch(addr.protocol.toLowerCase()){case'http:':req=http.request(options,resHandler);break;case'https:':req=https.request(options,resHandler);break;default:if(cb)cb(new Error('Invalid / unknown URL protocol. Expected HTTP or HTTPS.'),null);return;}if(typeof opts.timeout==='number'){req.setTimeout(opts.timeout,function(){req.abort();if(cb)cb(new Error('Timeout has been reached.'),null);cb=null})}req.on('error',function(err){if(cb)cb(err,null)});if(opts.hasOwnProperty('data')){var postData=opts.data;if(!(opts.data instanceof Buffer)&&_typeof(opts.data)==='object'){var contentType=options.headers['content-type']||options.headers['Content-Type'];if(contentType==='application/x-www-form-urlencoded'){postData=qs.stringify(opts.data)}else{try{postData=JSON.stringify(opts.data)}catch(err){if(cb)cb(new Error('Couldn\'t stringify object. (Likely due to a circular reference.)'),null)}}}req.write(postData)}req.end()};phin.promisified=function(opts,http){return new Promise(function(resolve,reject){phin(opts,function(err,res){if(err){reject(err)}else{resolve(res)}},http)})};if(util.promisify){phin[util.promisify.custom]=phin.promisified}module.exports=phin;

}, function(modId) {var map = {}; return __REQUIRE__(map[modId], modId); })
return __REQUIRE__(1595572138975);
})()
//# sourceMappingURL=index.js.map