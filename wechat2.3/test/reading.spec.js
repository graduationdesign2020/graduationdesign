const automator = require('miniprogram-automator')

describe('reading', () => {
  let miniProgram

  beforeAll(async () => {
    miniProgram = await automator.connect({
      wsEndpoint: 'ws://localhost:9420',
    })
  })

  afterAll(async () => {
    await miniProgram.disconnect()
  })

  it('reading', async() => {
    const page = await miniProgram.reLaunch('/pages/reading/reading?id=2')
    await page.waitFor(500)

    const title=await page.$('.title')
    expect(await title.wxml()).toContain('测试1')
    expect(await title.wxml()).toContain('2020-08-25 21:40:39')

    const content=await page.$('.content')
    expect(await content.wxml()).toContain('无需回复')

    const info=await page.$('.read-info')
    expect(await info.wxml()).toContain('未读 2 已读 1')

    const unread = await page.$$('.unread')
    expect(unread.length).toBe(2)
    const read1 = await page.$$('.read')
    expect(read1.length).toBe(0)

    const checked = await page.$('van-switch')
    await checked.trigger('change', false)
    await page.waitFor(200)

    const read2 = await page.$$('.read')
    expect(read2.length).toBe(1)
  })
  
})