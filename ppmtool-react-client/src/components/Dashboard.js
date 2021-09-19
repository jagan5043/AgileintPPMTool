import React, { Component } from 'react'
import ProjectItem from './project/ProjectItem'


class Dashboard extends Component {
    render() {
        return (
            <div className="alert alert-warning">
            <h1>Welcome to Dashboard</h1>
            <ProjectItem/>
            </div>)
    }
}

export default Dashboard;
