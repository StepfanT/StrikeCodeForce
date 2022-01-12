import React, { useContext, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import AuthContext from "../context/AuthContext";
import '../index.css';
import Alert from 'react-popup-alert'

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
        
    };

    // const [alert, setAlert] = React.useState({
    //     type: 'error',
    //     text: 'This is a alert message',
    //     show: false
    // });
//  onShowAlert();
    // function onShowAlert(type) {
    //     setAlert({
    //         type: type,
    //         text: 'Joined Activity',
    //         show: true
    //     })
    // };

    //
    return (
        <table className="table table-striped table-hover" >
            <Alert
                header={'Registration Successful'}
                btnText={'Close'}
                text={alert.text}
                type={alert.type}
                show={alert.show}
                pressCloseOnOutsideClick={true}
                showBorderBottom={true}
                alertStyles={{}}
                headerStyles={{}}
                textStyles={{}}
                buttonStyles={{}}
            />
            <thead>
                <tr>
                    <th>Activity</th>
                    <th>Date</th>
                    <th>Location</th>
                    <th>Description</th>
                    <th>Time</th>
                    <th>Max Participants</th>
                    <th>Min Participants</th>
                    <th>Created By</th>

                </tr>
            </thead>
            <tbody >
                {posts.map(post => (

                    <tr key={post.activityId} >
                        <td style={{ border: 'solid 1px black' }}>{post.activityName}</td>
                        <td style={{ border: 'solid 1px black' }}>{post.date}</td>
                        <td style={{ border: 'solid 1px black' }}>{post.location}</td>
                        <td style={{ border: 'solid 1px black' }}>{post.description}</td>
                        <td style={{ border: 'solid 1px black' }}>{post.time}</td>
                        <td style={{ border: 'solid 1px black' }}>{post.max}</td>
                        <td style={{ border: 'solid 1px black' }}>{post.min}</td>
                        <td style={{ border: 'solid 1px black' }}>{post.createBy}</td>
                        <td style={{ border: 'solid 1px black' }}>
                            {console.log(post)}
                            {userStatus.user.userId === post.userId ? (
                                <div className="float-right">
                                    <Link to={`/activity/detail/${post.activityId}`} className="btn btn-primary btn-sm">
                                        <i className="bi bi-pencil"></i> Edit Details
                                    </Link>
                                </div>
                            ) : (
                                <div className="float-right">
                                    <div onClick={() => joinActivity(post.activityId)} className="btn btn-success btn-sm">
                                        <i className="bi bi-pencil"></i> Join
                                    </div>
                                </div>
                            )}
                        </td>
                    </tr>
                ))}
            </tbody>
            <div>
                <Link to={`/`} className="btn btn-warning btn-sm">
                    Return to Dashboard
                </Link>
            </div>
        </table >
    );
};

export default Posts;