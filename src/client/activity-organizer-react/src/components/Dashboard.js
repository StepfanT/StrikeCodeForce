import React, { useEffect, useState, createContext, useContext } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import '../App.css';
import AuthContext from "../context/AuthContext"
import styled from 'styled-components';

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
    };

    const styles = {
        section: {
            fontFamily: "-apple-system",
            fontSize: "1rem",
            fontWeight: 1.5,
            lineHeight: 1.5,
            color: "#292b2c",
            backgroundColor: "#fff",
            padding: "0 2em"
        },
        wrapper: {
            textAlign: "center",
            maxWidth: "950px",
            margin: "0 auto",
            border: "1px solid #e6e6e6",
            padding: "40px 25px",
            marginTop: "50px"
        },
        avatar: {
            margin: "-90px auto 30px",
            width: "100px",
            borderRadius: "50%",
            objectFit: "cover",
            marginBottom: "0"
        },
        quote: {
            lineHeight: 1.5,
            fontWeight: 300,
            marginBottom: "25px",
            fontSize: "1.375rem"
        },
        data: {
            marginBottom: "0",
            fontWeight: 400,
            fontSize: "1rem",
            border: "1px solid black"
        },
        position: { fontWeight: 400 }
    };

    return (
        <>

            <h2>
                Dashboard
            </h2>
            <div>
                <table
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
                                <td style={styles.data}>{activity.activityName}</td>
                                <td style={styles.data}>{activity.location}</td>
                                <td style={styles.data}>{activity.date.join("/")}</td>
                                <td style={styles.data}>{activity.time}</td>
                                <td style={styles.data}>{activity.createBy}</td>
                                <td>
                                    {console.log(activity.date)}
                                    <div className="float-right">
                                        <div className="float-right">
                                            <button
                                                onClick={() => handleClick(activity.activityId)} className="btn btn-primary btn-sm">
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
