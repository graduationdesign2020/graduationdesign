const simulate = require('miniprogram-simulate')

test('components/navBar/navBar', () => {
    const id = simulate.load('C:/Users/peach/github/wechatAppUI/components/navBar/navBar') // 此处必须传入绝对路径
    const comp = simulate.render(id) // 渲染成自定义组件树实例

    const parent = document.createElement('parent-wrapper') // 创建父亲节点
    comp.attach(parent) // attach 到父亲节点上，此时会触发自定义组件的 attached 钩子

    const view = comp.querySelector('.navBar') // 获取子组件 view
    expect(view.dom.innerHTML).toBe('index.properties') // 测试渲染结果
})