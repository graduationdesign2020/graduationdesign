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
    const page = await miniProgram.reLaunch('/pages/myProfile/Center')

    const button = await page.$('.logout-button')
    button.tap()
    console.log(button.tagName)

    const dialog = await page.$('.confirm-dialog')

    console.log(await dialog.wxml())
  })
  
})