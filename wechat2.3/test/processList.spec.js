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

  it('myProfile', async() => {
    const page = await miniProgram.reLaunch('/pages/processList/processList')


    const data = await page.data();
    console.log(data.userData)
    console.log(data.processes)
    const switch1 = await page.$('.van-switch')
    console.log(switch1.wxml())
  })
  
})