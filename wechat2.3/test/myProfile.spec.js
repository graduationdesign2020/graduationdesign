// const automator = require('miniprogram-automator')

// describe('index', () => {
//   let miniProgram
//   let page

//   beforeAll(async () => {
//     miniProgram = await automator.launch({
//       cliPath:'D:\\Program Files\\Tencent\\微信web开发者工具\\cli.bat',
//       projectPath: './'
//     })
//     page = await miniProgram.reLaunch('/pages/myProfile/Center')
//     await page.waitFor(500)
//   }, 50000)
//   it('button', async () => {
//     const button = await page.$('.logout-button')
//     console.log(button.wxml())
//     expect(await button.wxml()).toContain('注销用户'); 
//   })
//   afterAll(async () => {
//     await miniProgram.close()
//   })
// })
