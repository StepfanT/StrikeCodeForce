import { Link, useNavigate, useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import AuthContext from "../context/AuthContext"
import { useContext } from "react";
import Pagination from '@material-ui/lab/Pagination';

export default function Browse() {
    const [userStatus, setUserStatus] = useContext(AuthContext);
    const [activities, setActivity] = useState([]);

    const joinActivity = (activityId) => {
        const init = {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }
        fetch('http://localhost:8080/api/activity/user/' + userStatus.user.userId + '/' + activityId, init)
            .then(response => response.json())
            .then(data => { console.log(data); })
            .catch(error => console.log(error));
    }

    const getActivities = () => {
        fetch('http://localhost:8080/api/activity')
            .then(response => response.json())
            .then(data => { setActivity(data); console.log(data); })
            .catch(error => console.log(error));

    };

    useEffect(() => {
        getActivities();
    }, []);

    // Imported Pagination Component from https://mui.com/components/pagination/ 
    return (
        <div>

            <h2 className="my-4">Activities</h2>
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>Activity Name</th>
                        <th>Date</th>
                        <th>Location</th>
                        <th>Description</th>
                        <th>Time</th>
                        <th>Max Participants</th>
                        <th>Min Participants</th>
                        <th>Created By</th>

                    </tr>
                </thead>
                <tbody>
                    {activities.map(activity => (
                        <tr key={activity.userId}>
                            <td>{activity.activityName}</td>
                            <td>{activity.date}</td>
                            <td>{activity.location}</td>
                            <td>{activity.description}</td>
                            <td>{activity.time}</td>
                            <td>{activity.max}</td>
                            <td>{activity.min}</td>
                            <td>{activity.createBy}</td>

                            <td>
                                {userStatus.user.userId == activity.userId ? (
                                    <div className="float-right">
                                        <Link to={`/activity/detail/${activity.activityId}`} className="btn btn-primary btn-sm">
                                            <i className="bi bi-pencil"></i> Edit
                                        </Link>
                                    </div>
                                ) : (
                                    <div className="float-right">
                                        <div onClick={() => joinActivity(activity.activityId)} className="btn btn-primary btn-sm">
                                            <i className="bi bi-pencil"></i> Join
                                        </div>
                                    </div>
                                )}
                                <div>
                                    <Link to={`/`} className="btn btn-success btn-sm">
                                        <i className="bi bi-pencil"></i>
                                        Return Home
                                    </Link>
                                </div>

                            </td>
                        </tr>

                    ))}
                    
                    <div
                        style={{ display: 'inline', padding: 30 }}>
                        <h4>Pagination</h4>
                        <Pagination count={5} />
                    </div>
                </tbody>
            </table>
        </div>
    );
}

/*


*/