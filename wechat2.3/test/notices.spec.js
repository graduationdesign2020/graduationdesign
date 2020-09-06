const automator = require('miniprogram-automator')

describe('notices', () => {
  let miniProgram

  beforeAll(async () => {
    miniProgram = await automator.connect({
      wsEndpoint: 'ws://localhost:9420',
    })
  })

  afterAll(async () => {
    await miniProgram.disconnect()
  })

  it('school', async() => {
    const page = await miniProgram.reLaunch('/pages/notices/notices?type=0')
    await page.waitFor(500)

    expect(await page.data('type')).toBe('0')

    const notices = await page.$$('wux-card')
    expect(notices.length).toBe(4)

    const notice=notices[0];
    expect(await notice.wxml()).toContain('')
    // expect(await notice.attribute('title')).toBe('')
    await notice.tap()
    await page.waitFor(500)
    expect((await miniProgram.currentPage()).path).toBe('pages/noticeDetail/noticeDetail')
  })

  it('department', async() => {
    const page = await miniProgram.reLaunch('/pages/notices/notices?type=1')
    await page.waitFor(500)

    expect(await page.data('type')).toBe('1')

    const notices = await page.$$('wux-card')
    expect(notices.length).toBe(26)

    const notice=notices[0];
    expect(await notice.wxml()).toContain('')
    // expect(await notice.attribute('title')).toBe('')
    await notice.tap()
    await page.waitFor(500)
    expect((await miniProgram.currentPage()).path).toBe('pages/noticeDetail/noticeDetail')
  })

  it('reading', async() => {
    const page = await miniProgram.reLaunch('/pages/notices/notices?type=2')
    await page.waitFor(500)
    expect(await page.data('type')).toBe('2')

    if(await page.data('auth')) {
      const notices = await page.$$('wux-card')
      expect(notices.length).toBe(2)
      
      const read=notices[1];
      expect(await read.wxml()).toContain('测试1')
      await read.tap()
      await page.waitFor(500)
      expect((await miniProgram.currentPage()).path).toBe('pages/reading/reading')
    }else {
      const notices = await page.$$('wux-card')
      expect(notices.length).toBe(2)
      
      const read=notices[1];
      expect(await read.wxml()).toContain('测试1')
      await read.tap()
      await page.waitFor(500)
      expect((await miniProgram.currentPage()).path).toBe('pages/noticeDetail/noticeDetail')
    }
  })

  it('reply', async() => {
    const page = await miniProgram.reLaunch('/pages/notices/notices?type=2')
    await page.waitFor(500)
    expect(await page.data('type')).toBe('2')

    if(await page.data('auth')) {
      const notices = await page.$$('wux-card')
      expect(notices.length).toBe(2)
      
      const reply=notices[0];
      expect(await reply.wxml()).toContain('测试2')
      await reply.tap()
      await page.waitFor(500)
      expect((await miniProgram.currentPage()).path).toBe('pages/studentReply/studentReply')
    }else {
      const notices = await page.$$('wux-card')
      expect(notices.length).toBe(2)
      
      const reply=notices[0];
      expect(await reply.wxml()).toContain('测试2')
      await reply.tap()
      await page.waitFor(500)
      expect((await miniProgram.currentPage()).path).toBe('pages/reply/reply')
    }
  })
  
})