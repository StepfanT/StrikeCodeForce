import React, { useEffect, useState, useContext } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import AuthContext from "../context/AuthContext"
import '../App.css';

export default function View() {

    const [activities, setActivity] = useState(AuthContext);
    const [userStatus, setUserStatus] = useContext(AuthContext);

    const [activityName, setActivityName] = useState('');
    const [description, setDescription] = useState('');
    const [location, setLocation] = useState('');
    const [date, setDate] = useState('');
    const [time, setTime] = useState('');
    const [userId, setUserId] = useState('');
    const [maxParticipant, setMaxParticipant] = useState('');
    const [minParticipant, setMinParticipant] = useState('');
    const [createBy, setCreateBy] = useState('');

    const { activityId } = useParams();


    useEffect(() => {
        fetch('http://localhost:8080/api/activity/')
            .then(response => {
                if (response.status === 404) {
                    return Promise.reject(`Received 404 Not Found for Activity name: ${activityId.activityName}`);
                }
                return response.json();
            })
            .then(data => {
                setActivity(data);
                setActivityName(data.activityName);
                setDescription(data.description);
                setLocation(data.location);
                setDate(data.data);
                setTime(data.time);
                setUserId(data.userId);
                setMaxParticipant(data.maxParticipant);
                setMinParticipant(data.minParticipant);
                setCreateBy(data.createBy);
            })
            .catch(error => {
                console.log(error);
            });
    }, [activityId]);

    const navigate = useNavigate();

    const handleClick = () => {
        navigate(`/activity/detail/${activityId}`,{state: {from: "View" } }  ) 


    }



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
                                        {/* <Link 
                                            to={'/activity/detail/${activity.activityId}'}
                                            state={ {from: 'occupation' } }                                           
                                            className="btn btn-primary btn-sm">
                                            <i className="bi bi-pencil"></i>
                                            View/Edit Details
                                        </Link> */}
                                        <button onClick={handleClick} className="btn btn-primary btn-sm">
                                            View/Edit Details
                                        </button>


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