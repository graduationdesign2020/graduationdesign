const automator = require('miniprogram-automator')

describe('e2e tests', () => {
  let miniProgram

  beforeAll(async () => {
    miniProgram = await automator.connect({
      wsEndpoint: 'ws://localhost:9420',
    })
  })

  afterAll(async () => {
    await miniProgram.disconnect()
  })

  it('register', async() => {
    const page = await miniProgram.reLaunch('/pages/register/index')

    const namefield = await page.$('#field-name')
    console.log(await namefield.wxml())
    // 首先确保开发者工具运行在9420端口
    // ./cli --auto <文件路径> --auto-port 9420
    // 运行的时候终端在wechatAppUI目录下输入 ./node_modules/.bin/jest index.spec.js 即运行这个测试文件
    // 也可以自己定义命令jest 或者 npm test
    // await不可以省略，从页面或者program得到都需要await（等待直至拿到）
    // it 的第一个参数是测试描述，我们就按照页面的名字命名吧
    // 因为用的是ui库所以用selector选取元素的时候要看源代码
    // elemnet的方法可以看 https://developers.weixin.qq.com/miniprogram/dev/devtools/auto/element.html （cmd+单击可访问）
  })
  
})