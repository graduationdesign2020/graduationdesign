const automator = require('miniprogram-automator')

describe('reply', () => {
  let miniProgram

  beforeAll(async () => {
    miniProgram = await automator.connect({
      wsEndpoint: 'ws://localhost:9420',
    })
  })

  afterAll(async () => {
    await miniProgram.disconnect()
  })

  it('reply', async() => {
    const page = await miniProgram.reLaunch('/pages/SendNotice/SendNotice')
    await page.setData({
      title:"title"
    })
    await page.waitFor(500)
    await page.callMethod("chooseAll")
    await page.waitFor(500)
    await page.setData({
      text: "content"
    })
    await page.waitFor(500)
    const button = await page.$('van-button')
    button.tap()
    console.log(button.tagName)
  })
  
})