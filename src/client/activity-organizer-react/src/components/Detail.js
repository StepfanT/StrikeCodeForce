import { Link, useNavigate, useParams } from 'react-router-dom';
import React, { useEffect, useState, useContext } from "react";
import { getData } from "../testData";
import AuthContext from "../context/AuthContext"
// import Errors from './Errors';

export default function Detail() {
    //let activityData = getData();

    const [activityData, setActivityDetails] = useState([]);
    const [userStatus, setUserStatus] = useContext(AuthContext);
    const history = useNavigate();
    //There is no specific activity getter. 
    //Get activity data via userId then filter via activityId?
    const getActivityData = () => {
        fetch('http://localhost:8080/api/activity/' + userStatus.activity.activityId)
            .then(response => response.json())
            .then(data => { setActivityDetails(data); console.log(data); })
            .catch(error => console.log(error));
    };
    useEffect(() => {

        getActivityData();
    }, []);


    const editActivityFormSubmitHandler = (event) => {
        event.preventDefault();

        const updatedDetail = {
            activityData
        };
        const init = {
            method: 'PUT',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedDetail)
        };
        fetch(`http://localhost:8080/api/activity/${updatedDetail.activityId}`, init)
            .then(response => {
                if (response.status === 204) {
                    return null;
                } else if (response.status === 400) {
                    return response.json();
                }
                return Promise.reject('Something unexpected went wrong ');
            })
            .then(data => {
                if (!data) {
                    history('/');
                } else {
                    // setErrors(data);
                    console.log("This is where the errors would be!")
                }
            })
            .catch(error => console.log(error));
    };

return (
    <>
        <style>{"table{border:1px solid black;}"}
        </style>
        <form onSubmit={editActivityFormSubmitHandler}>
            <div>
                <h2 className="my-4">Activities</h2>
                <div>
                    <label htmlFor="activityName">Activity Name</label>
                    <input type="text" id="activityName" name="activityName"
                        value={activityData.activityName} onChange={editActivityFormSubmitHandler}
                        placeholder={activityData.activityName}
                    />
                </div>

                <div>
                    <label htmlFor="description">Description</label>
                    <input type="description" id="description" name="description"
                        value={activityData.description} onChange={editActivityFormSubmitHandler} />
                </div>

                <div>
                    <label htmlFor="location">Location of Activity</label>
                    <input type="text" id="location" name="location"
                        value={activityData.location} onChange={editActivityFormSubmitHandler} />
                </div>

                <div>
                    <label htmlFor="date">Date</label>
                    <input type="date" id="date" name="date"
                        value={activityData.date} onChange={editActivityFormSubmitHandler} />
                </div>

                <div>
                    <label htmlFor="time">Time</label>
                    <input type="time" id="time" name="time"
                        value={activityData.time} onChange={editActivityFormSubmitHandler} />
                </div>

                <div>
                    <label htmlFor="maxParticipant">Max # of Participants</label>
                    <input type="number" pattern="[0-9]*" id="maxParticipant"
                        name="maxParticipant" min="6" max="50"
                        value={activityData.maxParticipant} onChange={editActivityFormSubmitHandler} />
                </div>
                <div>
                    <label htmlFor="minParticipant">Min # of Participants</label>
                    <input type="number" pattern="[0-9]*" id="minParticipant"
                        name="minParticipant" min="3" max="45"
                        value={activityData.minParticipant} onChange={editActivityFormSubmitHandler} />
                </div>

                <div>
                    <label htmlFor="createBy">Created By</label>
                    <input type="text" id="createBy" name="createBy"
                        value={activityData.createBy} />
                </div>

                <div className="mt-5">
                    <button className="btn btn-info" type="submit">
                        <i className="bi bi-save"></i> Update Activity</button>

                    <Link to="/activity" className="btn btn-warning ml-2">
                        <i className="bi bi-x"></i> Cancel
                    </Link>

                </div>
                <Link to={`/activity/browse`} className="btn btn-success btn-sm">
                    Return to List
                </Link>
            </div>
        </form>
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