try {
    require("source-map-support").install();
} catch(err) {
}
require("./out.dev/goog/bootstrap/nodejs.js");
require("./out.dev/hello_world.js");
goog.require("hello_world.dev");
goog.require("cljs.nodejscli");
