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
    const page = await miniProgram.reLaunch('/pages/SendNotice/SendNotice')
    await page.setData({
      title:"title"
    })
    await page.callMethod("chooseAll")
    await page.setData({
      text: "content"
    })
    const button = await page.$('.send-button')
    button.tap()
    console.log(button.tagName)
  })
  
})