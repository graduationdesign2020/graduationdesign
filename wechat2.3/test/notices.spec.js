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
    const page = await miniProgram.reLaunch('/pages/notices/notices?type=1')
    await page.waitFor(async () => {
      return (await page.$$('wux-card')).length > 2
    })
    const elements = await page.$$('wux-card')
    console.log(elements.length)
    const button=elements[0];
    button.tap()
    console.log(button.tagName)

  })
  
})