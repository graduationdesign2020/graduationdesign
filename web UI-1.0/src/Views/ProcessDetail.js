import React from "react";
import { Layout, Menu, Table, Tag } from 'antd';
import {
    DesktopOutlined,
    BarsOutlined
} from '@ant-design/icons';
import '../css/PrecessDetail.css';
import {ajax} from "../utils/ajax";
const { Header, Content, Sider } = Layout;

class ProcessDetail extends React.Component {
    state = {
        collapsed: false,
        choose: 1,
        process: [{state: 1, stuInfos: [
                {"name": "李华", "flag": true, "instructor": "导师1", "project": "项目1"},
                {"name": "小明", "flag": true, "instructor": "导师1", "project": "项目2"},
                {"name": "小红", "flag": false, "instructor": "导师2", "project": "项目3"},
                {"name": "小强", "flag": true, "instructor": "导师3", "project": "项目4"},
                {"name": "小张", "flag": false, "instructor": "导师4", "project": "项目5"},
                {"name": "小李", "flag": true, "instructor": "导师5", "project": "项目6"}
            ]},
            {state: 2, stuInfos: [
                    {"name": "hh", "flag": true, "instructor": "导师1", "project": "项目1"},
                    {"name": "gfs", "flag": true, "instructor": "导师1", "project": "项目2"},
                    {"name": "gerg", "flag": false, "instructor": "导师2", "project": "项目3"},
                    {"name": "dggn", "flag": true, "instructor": "导师3", "project": "项目4"},
                    {"name": "ngf", "flag": true, "instructor": "导师4", "project": "项目5"},
                    {"name": "liuli", "flag": true, "instructor": "导师5", "project": "项目6"}
                ]},
            {state: 3, stuInfos: [
                    {"name": "hh", "flag": true, "instructor": "导师1", "project": "项目1"},
                    {"name": "gfs", "flag": true, "instructor": "导师1", "project": "项目2"},
                    {"name": "gerg", "flag": false, "instructor": "导师2", "project": "项目3"},
                    {"name": "dggn", "flag": true, "instructor": "导师3", "project": "项目4"},
                    {"name": "ngf", "flag": true, "instructor": "导师4", "project": "项目5"},
                    {"name": "liuli", "flag": true, "instructor": "导师5", "project": "项目6"}
                ]},
            {state: 4, stuInfos: [
                    {"name": "hh", "flag": true, "instructor": "导师1", "project": "项目1"},
                    {"name": "gfs", "flag": true, "instructor": "导师1", "project": "项目2"},
                    {"name": "gerg", "flag": false, "instructor": "导师2", "project": "项目3"},
                    {"name": "dggn", "flag": false, "instructor": "导师3", "project": "项目4"},
                    {"name": "ngf", "flag": false, "instructor": "导师4", "project": "项目5"},
                    {"name": "liuli", "flag": false, "instructor": "导师5", "project": "项目6"}
                ]},
            {state: 5, stuInfos: [
                    {"name": "hh", "flag": false, "instructor": "导师1", "project": "项目1"},
                    {"name": "gfs", "flag": false, "instructor": "导师1", "project": "项目2"},
                    {"name": "gerg", "flag": false, "instructor": "导师2", "project": "项目3"},
                    {"name": "dggn", "flag": false, "instructor": "导师3", "project": "项目4"},
                    {"name": "ngf", "flag": false, "instructor": "导师4", "project": "项目5"},
                    {"name": "liuli", "flag": false, "instructor": "导师5", "project": "项目6"}
                ]}
        ]
    };

    componentDidMount() {
        const callback = (data) => {
            this.setState({process: data})
        }
        ajax("getStudentProcess", "123", callback)
    }

    onCollapse = collapsed => {
        console.log(collapsed);
        this.setState({ collapsed });
    };

    title = () => {
        if(this.state.choose === 1){
            return "任务书"
        }
        if(this.state.choose === 2){
            return "开题报告"
        }
        if(this.state.choose === 3){
            return "中期检查"
        }
        if(this.state.choose === 4){
            return "论文定稿"
        }
        if(this.state.choose === 5){
            return "论文最终稿"
        }
        if(this.state.choose === 6){
            return "用户管理"
        }
    };

    render() {
        const columns = [
            {title: "姓名", dataIndex: "name"},
            {title: "状态", dataIndex: "flag", render: record => (record) ? <Tag color={"green"}>已完成</Tag> : <Tag color={"red"}>未完成</Tag> },
            {title: "导师", dataIndex: "instructor"},
            {title: "毕业设计课题", dataIndex: "project"}
        ]
        return (
            <Layout style={{ minHeight: '100vh' }}>
                <Sider collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
                    <div className="logo" />
                    <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" onClick={(e)=>{this.setState({choose: e.key})}}>
                        <Menu.Item key="1" icon={<BarsOutlined />} >
                            任务书
                        </Menu.Item>
                        <Menu.Item key="2" icon={<DesktopOutlined />}>
                            开题报告
                        </Menu.Item>
                        <Menu.Item key="3" icon={<DesktopOutlined />}>
                            中期检查
                        </Menu.Item>
                        <Menu.Item key="4" icon={<DesktopOutlined />}>
                            论文定稿
                        </Menu.Item>
                        <Menu.Item key="5" icon={<DesktopOutlined />}>
                            论文最终稿
                        </Menu.Item>
                        <Menu.Item key="6" icon={<DesktopOutlined />}>
                            用户管理
                        </Menu.Item>
                    </Menu>
                </Sider>
                <Layout className="site-layout">
                    {/*<Header className="site-layout-background" style={{ padding: 0 }} />*/}
                    <Content style={{ margin: '0 16px' }}>
                        <div style={{ marginTop: '25px' }}>
                            <h1>{this.title()}</h1>
                        </div>
                        <Table dataSource={this.state.process[this.state.choose - 1].stuInfos} columns={columns}/>
                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default ProcessDetail;
