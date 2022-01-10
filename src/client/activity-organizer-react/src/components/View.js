import React, { useEffect, useState, useContext } from 'react';
import { useParams, Link } from 'react-router-dom';
import { getData } from "../testData";
import AuthContext from "../context/AuthContext"
import '../App.css';

export default function View() {

    const [activities, setActivity] = useState([]);
    const [userStatus, setUserStatus] = useContext(AuthContext);

    const getData = () => {
        fetch('http://localhost:8080/api/activity/' + userStatus.user.userId)
            .then(response => response.json())
            .then(data => setActivity(data))
            .catch(error => console.log(error));            
    };
    useEffect(() => {
        getData();
    }, []);  
    return (
        <>
            <style>{"table{border:1px solid black;}"}
            </style>
            <div>
                <h2 className="my-4">Activities</h2>
                <table style={{ "borderWidth": "1px", 'borderColor': "#aaaaaa", 'borderStyle': 'solid' }}
                    className="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Activity</th>
                            <th>Location</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Owner</th>
                        </tr>
                    </thead>
                    <tbody>
                        {activities.map(activity => (
                            <tr key={activity.activityId}>
                                <td>{activity.activityName}</td>
                                <td>{activity.location}</td>
                                <td>{activity.date}</td>
                                <td>{activity.time}</td>
                                <td>{activity.createBy}</td>
                                <td>
                                    <div className="float-right">
                                        <Link to={`/activity/detail/${activity.activityId}`} className="btn btn-primary btn-sm">
                                            <i className="bi bi-pencil"></i> View Details
                                        </Link>
                                    </div>

                                    <div className="float-right">
                                        <Link to={`/activity/delete/${activity.activityId}`} className="btn btn-danger btn-sm">
                                            <i className="bi bi-pencil"></i> Delete Activity
                                        </Link>
                                    </div>


                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <Link to={`/`} className="btn btn-success btn-sm">
                    Return Home
                </Link>
            </div>
        </>
    );
}