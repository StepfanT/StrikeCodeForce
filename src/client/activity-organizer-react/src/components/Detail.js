import { Link, useNavigate, useParams } from 'react-router-dom';
import React, { useEffect, useState, useContext } from "react";
import { getData } from "../testData";
import AuthContext from "../context/AuthContext"
// import Errors from './Errors';

export default function Detail() {
    //let activityData = getData();

    const [activityData, setActivityDetails] = useState([]);
    const [userStatus, setUserStatus] = useContext(AuthContext);


    const getActivityData = () => {
        fetch('http://localhost:8080/api/activity/')
            .then(response => response.json())
            .then(data => {setActivityDetails(data); console.log(data);})
            .catch(error => console.log(error));
    };
    useEffect(() => {
        //heloel
        getActivityData();
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
                            <th>Description</th>
                            <th>Max Participants</th>
                            <th>Min Participants</th>
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
                                <td>{activity.description}</td>
                                <td>{activity.maxParticipant}</td>
                                <td>{activity.minParticipant}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <Link to={`/activity/browse`} className="btn btn-success btn-sm">
                    Return to List
                </Link>
            </div>
        </>
    );
};



/*


       setActivityName(data.activityName);
                setDescription(data.description);
                setLocation(data.location);
                setDate(data.data);
                setTime(data.time);
                setUserId(data.userId);
                setMaxParticipant(data.maxParticipant);
                setMinParticipant(data.minParticipant);
                setCreateBy(data.createBy);  

                
    const activityNameOnChangeHandler = (event) => {
        setActivityName(event.target.value);
    };
    const descriptionOnChangeHandler = (event) => {
        setDescription(event.target.value);
    };

    const locationOnChangeHandler = (event) => {
        setLocation(event.target.value);
    };
    const dateOnChangeHandler = (event) => {
        setDate(event.target.value);
    };
    const timeOnChangeHandler = (event) => {
        setTime(event.target.value);
    };

    const maxParticipantOnChangeHandler = (event) => {
        setMaxParticipant(event.target.value);
    };
    const minParticipantOnChangeHandler = (event) => {
        setMinParticipant(event.target.value);
    };

*/