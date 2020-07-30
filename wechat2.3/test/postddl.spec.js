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
    const page = await miniProgram.reLaunch('/pages/postddl/postddl')

    await page.setData({
      value:3,
    })
    await page.setData({
      currentDateString: "2020-08-02",
      currentTime:"23:59"
    })
    await page.waitFor(500)
    const button = await page.$('.sendbutton')
    button.tap()
    console.log(button.tagName)
  })
  
})