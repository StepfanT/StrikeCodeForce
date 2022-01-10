import { Link, useNavigate, useParams } from 'react-router-dom';
import React, { useEffect, useState, useContext } from "react";
import { getData } from "../testData";
import AuthContext from "../context/AuthContext"
// import Errors from './Errors';

export default function Delete() {

    const [activityData, setActivityDetails] = useState([]);
    const [userStatus, setUserStatus] = useContext(AuthContext);
    const history = useNavigate();
    const { id } = useParams();

    const [deleteActivityId, setDeleteActivityId] = useState('');
    const [activityName, setActivityName] = useState('');
    const [description, setDescription] = useState('');
    const [location, setLocation] = useState('');
    const [date, setDate] = useState('');
    const [time, setTime] = useState('');
    const [userId, setUserId] = useState('');
    const [maxParticipant, setMaxParticipant] = useState('');
    const [minParticipant, setMinParticipant] = useState('');
    const [createBy, setCreateBy] = useState('');

    //same issue as in Details page,
    //There is no specific activity getter. 
    //Get activity data via userId then filter via activityId?

    useEffect(() => {
        fetch(`http://localhost:8080/api/activity/${id}`)
            .then(response => response.json())
            .then(data => {
                setActivityDetails(data);
                console.log(data);
            })
            .catch(error => console.log(error));
    }, [id]);


    const deleteActivitySubmitHandler = (event) => {
        event.preventDefault();

        const deleteActivity = {
            activityName,
            description,
            location,
            date,
            time,
            userId,
            maxParticipant,
            minParticipant
        };

        const init = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(deleteActivity)
        };
        fetch(`http://localhost:8080/api/activity/${id}`, init)
            .then(response => {
                if (response.status === 204) {
                    return null;
                } else if (response.status === 400) {
                    return response.json();
                }
                return Promise.reject('Something unexpected went wrong :)');
            })
            .then(data => {
                if (!data) {
                    history('/');
                } else {
                    // we have errors to display                  
                    //    setErrors(data);
                    console.log("this is where errors display");
                }
            })
            .catch(error => console.log(error));
    };

    return (
        <>
            <style>{"table{border:1px solid black;}"}
            </style>
            <form onSubmit={deleteActivitySubmitHandler}>
                <h2 className="my-4">Activities</h2>
                <div>
                    <label htmlFor="activityName">Activity Name</label>
                    <input type="text" id="activityName" name="activityName"
                        value={activityName} readOnly
                    />
                </div>

                <div>
                    <label htmlFor="description">Description</label>
                    <input type="description" id="description" name="description"
                        value={description} readOnly />
                </div>

                <div>
                    <label htmlFor="location">Location of Activity</label>
                    <input type="text" id="location" name="location"
                        value={location} readOnly />
                </div>

                <div>
                    <label htmlFor="date">Date</label>
                    <input type="date" id="date" name="date"
                        value={date} readOnly />
                </div>

                <div>
                    <label htmlFor="time">Time</label>
                    <input type="time" id="time" name="time"
                        value={time} readOnly />
                </div>

                <div>
                    <label htmlFor="maxParticipant">Max # of Participants</label>
                    <input type="number" pattern="[0-9]*" id="maxParticipant"
                        name="maxParticipant" min="6" max="50"
                        value={maxParticipant} readOnly />
                </div>
                <div>
                    <label htmlFor="minParticipant">Min # of Participants</label>
                    <input type="number" pattern="[0-9]*" id="minParticipant"
                        name="minParticipant" min="3" max="45"
                        value={minParticipant} readOnly />
                </div>

                <div>
                    <label htmlFor="createBy">Created By</label>
                    <input type="text" id="createBy" name="createBy"
                        value={createBy} readOnly />
                </div>

                <div className="mt-5">
                    <div className="mt-5">
                        <button className="btn btn-danger" type="submit">
                            <i className="bi bi-save"></i> Delete Activity?</button>

                        <Link to="/" className="btn btn-warning ml-2">
                            <i className="bi bi-x"></i> Cancel
                        </Link>
                    </div>
                </div>
            </form>
        </>
    );
};