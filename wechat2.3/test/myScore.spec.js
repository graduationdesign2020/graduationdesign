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

  it('myScore', async() => {
    const page = await miniProgram.reLaunch('/pages/myScore/myScore')

    const data = await page.data();
    console.log(data.teachergrade)

    console.log(data.thesisgrade)

    console.log(data.reviewgrade)

    console.log(data.allgrade)

    


  })
  
})