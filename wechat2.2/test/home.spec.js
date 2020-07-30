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

  it('home', async() => {
    const page = await miniProgram.reLaunch('/pages/home/home')

    const namefield = await page.$('common-app')
    console.log(await namefield.wxml())

  })
  
})