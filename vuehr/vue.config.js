// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })
let proxyObj = {};
proxyObj['/'] = {
  ws: false,
  target: 'http://localhost:8081',//目标地址
  changeOrigin: true,
  pathRewrite: {
    '^/': ''
  }
}

module.exports = {
  devServer: {
    host: 'localhost',
    port: 8080,//本服务的信息
    proxy: proxyObj
  }
}
