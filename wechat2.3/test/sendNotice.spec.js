const automator = require('miniprogram-automator')

describe('send notice', () => {
  let miniProgram

  beforeAll(async () => {
    miniProgram = await automator.connect({
      wsEndpoint: 'ws://localhost:9420',
    })
  })

  afterAll(async () => {
    await miniProgram.disconnect()
  })

  it('read', async() => {
    const page = await miniProgram.reLaunch('/pages/SendNotice/SendNotice')
    await page.waitFor(500)

    await page.setData({
      title:"测试",
      text: "测试",
    })
    await page.waitFor(200)

    await page.callMethod("chooseAll")
    await page.waitFor(200)
    expect(await page.data('result.length')).toBe(3)

    const button = await page.$('.send-button')
    await button.tap()
    await page.waitFor(500)
    expect(await page.data('msg')).toBe('发送成功')
  }, 30000)

  it('reply', async() => {
    const page = await miniProgram.reLaunch('/pages/SendNotice/SendNotice')
    await page.waitFor(500)

    await page.setData({
      title:"测试",
      text: "测试",
      task: '测试'
    })
    await page.waitFor(200)

    await page.callMethod("chooseAll")
    await page.waitFor(200)
    expect(await page.data('result.length')).toBe(3)

    const taskButton = await page.$('.task-button')
    await taskButton.tap()
    await page.waitFor(200)
    const tasks = await page.$$('van-tag')
    expect(tasks.length).toBe(1)

    const button = await page.$('.send-button')
    await button.tap()
    await page.waitFor(500)
    expect(await page.data('msg')).toBe('发送成功')
  }, 30000)
  
})