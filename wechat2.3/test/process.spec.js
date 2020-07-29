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
    const page = await miniProgram.reLaunch('/pages/process/procedure')
   const data = await page.data();
   console.log(data.states.length)
    console.log(data.states[0])

    console.log(data.states[1])

    console.log(data.states[2])

    console.log(data.states[3])

    console.log(data.states[4])
  })
  
})