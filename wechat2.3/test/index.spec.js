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

  it('register-stu', async() => {
    const page = await miniProgram.reLaunch('/pages/register/index')
    
    const name = await page.$('.name-field-stu')
    const id = await page.$('.id-field-stu')

    const the_input = page.$('. van-field__input')
    console.log(await name.wxml())
    console.log(the_input.tagName)
    await the_input.input("许嘉琦")
    // await id.input("518021910515")

    // const button = await page.$('.submit-button')
    // await button.tap()

    // await page.waitFor(1500);
    // const dialog = await page.$('.confirm')
    // await dialog.tap()

    // await page.waitFor(1500);
    // const currentPage = await miniProgram.currentPage();
    // expect(currentPage.path).toContain('pages/home/home');
  })

  it('myProfile', async() => {
    const page = await miniProgram.reLaunch('/pages/myProfile/Center')

    const button = await page.$('.logout-button')
    expect(await button.wxml()).toContain('注销用户'); 
    await button.tap()

    await page.waitFor(1500);
    const dialog = await page.$('.confirm-dialog')
    await dialog.tap()

    await page.waitFor(1500);
    const currentPage = await miniProgram.currentPage();
    expect(currentPage.path).toContain('pages/register/index');
  })
  
})