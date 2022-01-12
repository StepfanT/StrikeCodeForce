import React, { useContext } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import AuthContext from "../context/AuthContext"

// Repurposed Pagination/Posts code came from https://www.youtube.com/watch?v=IYCa1F-OWmk
const Posts = ({ posts, loading }) => {
    const [userStatus, setUserStatus] = useContext(AuthContext);
    if (loading) {
        return <h2> Loading...</h2>
    }
    
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
    return (
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
                {posts.map(post => (
                    <tr key={post.id} >
                        <td>{post.activityName}</td>
                        <td>{post.date}</td>
                        <td>{post.location}</td>
                        <td>{post.description}</td>
                        <td>{post.time}</td>
                        <td>{post.max}</td>
                        <td>{post.min}</td>
                        <td>{post.createBy}</td>
                        <td>

                            {userStatus.user.userId == post.userId ? (
                                <div className="float-right">
                                    <Link to={`/activity/detail/${post.activityId}`} className="btn btn-primary btn-sm">
                                        <i className="bi bi-pencil"></i> Edit
                                    </Link>
                                </div>
                            ) : (
                                <div className="float-right">
                                    <div onClick={() => joinActivity(post.activityId)} className="btn btn-primary btn-sm">
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
            </tbody>
        </table >
    );
};

export default Posts;