import React, { useEffect, useState, createContext, useContext } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import '../App.css';
import AuthContext from "../context/AuthContext"

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
        if (userStatus.user != null)
            getActivity();
    }, []);

    const navigate = useNavigate();

    const handleClick = (activityId) => {
        navigate('/activity/detail/' + activityId, { state: { id: userStatus.user.userId } })
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
                                <td>{activity.date.join("/")}</td>
                                <td>{activity.time}</td>
                                <td>{activity.createBy}</td>
                                <td>
                                {console.log(activity.date)}
                                    <div className="float-right">
                                        <div className="float-right">
                                            <button onClick={() => handleClick(activity.activityId)} className="btn btn-primary btn-sm">
                                                Details
                                            </button>
                                        </div>

                                    </div>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>
    );

}
