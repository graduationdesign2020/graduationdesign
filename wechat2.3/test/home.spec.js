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
    const page = await miniProgram.reLaunch('/pages/home/home')
    const elements = await page.$$('van-grid-item')
    console.log(elements.length)
    const button0=elements[0];
    button0.tap()
  
    const schoolnotices=await page.$$('.schoolnotices')
    console.log(schoolnotices.length)
    const deptnotices=await page.$$('.deptnotices')
    console.log(deptnotices.length)
    const messages=await page.$$('.messages')
    console.log(messages.length)
    

  })
  
})