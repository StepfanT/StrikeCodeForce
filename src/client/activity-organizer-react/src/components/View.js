import React, { useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { getData } from "../testData";
import '../App.css';

export default function View() {

    //  const [activities, setActivity] = useState([]);
    // implement when server is running
    // const getData = () => {
    //     fetch('http://localhost:8080/activity')
    //         .then(response => response.json())
    //         .then(data => setAgents(data))
    //         .catch(error => console.log(error));
    // };    
    // useEffect(() => {
    //     getData();
    // }, []);

    // this is the test data that we 'fetch' for testing while server is not yet running
    let activityData = getData();

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
                        {activityData.map(activity => (
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
