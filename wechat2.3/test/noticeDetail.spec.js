const automator = require('miniprogram-automator')

describe('notice detail', () => {
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
    const page = await miniProgram.reLaunch('/pages/noticeDetail/noticeDetail?type=0&id=1')
    await page.waitFor(500)

    expect(await page.data('type')).toBe('0')

    const title=await page.$('.detail__title')
    expect(await title.text()).toBe('欢迎同学们在系统中对指导教师进行评价')

    const time=await page.$('.article__time')
    expect(await time.text()).toBe('2016-06-01 00:00:00')

    const content=await page.$('.article__content')
    expect(await content.text()).toBe('同学你好：  为了解我校教师对毕业设计（论文）的指导投入、指导水平和指导效果，特开展此次调研，调研结 果仅由教务处掌握。希望同学能客观填写问卷，以便我们能了解真实的状况并制定相应改进措施。                                教务处                              2016年6月1日')
  })

  it('department', async() => {
    const page = await miniProgram.reLaunch('/pages/noticeDetail/noticeDetail?type=1&id=1')
    await page.waitFor(500)

    expect(await page.data('type')).toBe('1')

    const title=await page.$('.detail__title')
    expect(await title.text()).toBe('14级微电子科学与工程本科毕设宣讲材料')

    const time=await page.$('.article__time')
    expect(await time.text()).toBe('2017-11-13 00:00:00')

    const content=await page.$('.article__content')
    expect(await content.text()).toBe('同学你好：  为了解我校教师对毕业设计（论文）的指导投入、指导水平和指导效果，特开展此次调研，调研结 果仅由教务处掌握。希望同学能客观填写问卷，以便我们能了解真实的状况并制定相应改进措施。                                教务处                              2016年6月1日')
  })

  it('teacher', async() => {
    const page = await miniProgram.reLaunch('/pages/noticeDetail/noticeDetail?type=2&id=2&reading_id=1')
    await page.waitFor(500)

    expect(await page.data('type')).toBe('2')

    const title=await page.$('.detail__title')
    expect(await title.text()).toBe('测试1')

    const time=await page.$('.article__time')
    expect(await time.text()).toBe('2020-08-25 21:40:39')

    const content=await page.$('.article__content')
    expect(await content.text()).toBe('无需回复')

    const teacher=await page.$('.article__teacher')
    expect(await teacher.text()).toBe('步丰林')
  })
  
})