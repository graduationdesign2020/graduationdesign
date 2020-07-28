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
    const page = await miniProgram.reLaunch('/pages/reading/reading?id=24')
    await page.setData({
      checked: false
    })

  })
  
})