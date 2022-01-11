import React, { useEffect, useState, createContext, useContext } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import '../App.css';
import Login from "./Login";
import AuthContext from "../context/AuthContext"
import ReactDOM from "react-dom";


export default function Dashboard() {

    const [activities, setActivity] = useState([]);
    const [userActivities, setUserActivities] = useState([]);

    const { activityId } = useParams();
    const history = useNavigate();
    const [userStatus, setUserStatus] = useContext(AuthContext);

    const getActivity = () => {
        const init = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }
        fetch('http://localhost:8080/api/activity/user/' + userStatus.user.userId, init)
            .then(response => response.json())
            .then(data => { setActivity(data); console.log(data); })
            .catch(error => console.log(error));
    };

    useEffect(() => {
        getActivity();
    }, []);

    const getActivitiesFromUser = () => {
        fetch('http://localhost:8080/api/activity/user/' + userStatus.user.userId)
            .then(response => {
                if (response.status === 404) {
                    return Promise.reject(`Received 404 Not Found for Activity name`);
                }
                return response.json();
            })
            .then(data => {
                //setUserActivities(data);
                console.log(data);
            })
            .catch(error => {
                console.log(error);
            });
    }

    const navigate = useNavigate();

    const handleClick = () => {
        navigate('/activity/detail/'+ userStatus.user.userId, { state: { id: userStatus.user.userId } })
    }

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
                                        {/* <Link
                                            to={`/activity/detail/${activity.activityId}`}
                                            state={{ activityId }}
                                            className="btn btn-primary btn-sm">
                                            <i className="bi bi-pencil"></i> View Details
                                        </Link> */}

                                        <button onClick={handleClick} className="btn btn-primary btn-sm">
                                            View/Edit Details
                                        </button>
                                    </div>
                                    <button onClick={getActivitiesFromUser}
                                        className="btn btn-primary btn-sm">
                                        User Activity
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>
    );

}
