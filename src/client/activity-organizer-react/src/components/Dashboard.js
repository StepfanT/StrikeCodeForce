import React, { useEffect, useState } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import '../App.css';
import Login from "./Login";

export default function Dashboard(props) {
    const [activities, setActivity] = useState([]);


    const { id } = useParams();
    const history = useNavigate();
    


    const getActivity = () => {
        fetch(`http://localhost:8080/api/activity`)
            .then(response => response.json())
            .then(data => {setActivity(data);console.log(data);})
            .catch(error => console.log(error));
    };

    useEffect(() => {
        getActivity();
    }, []);


    return (
        <>
            <style>{"table{border:1px solid black;}"}
            </style>
            <div>
                <h2 className="my-4">Dashboard </h2>
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
