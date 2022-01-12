import {useEffect,useState} from 'react';


export default function Participants(props){

    const [participants,setParticipants]= useState([]);
    //on effect load get user participants from user_activity
    const getParticipants = () => {
        const init = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }
        fetch('http://localhost:8080/api/activity/participants/' + props.activityId, init)
            .then(response => response.json())
            .then(data => { setParticipants(data);console.log(data); })
            .catch(error => console.log(error));
    };

    useEffect(()=>{getParticipants()},[])

    const checkInParticipant = (userId)=>{
     const init = {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("token")
            }
        }
        fetch('http://localhost:8080/api/activity/participants/' + props.activityId+"/"+userId, init)
            .then(response => response.json())
            .then(data => { setParticipants(data);console.log(data); })
            .catch(error => console.log(error));
       
    }
    return (
        <div>
            <h2>
            Participants
            </h2>
            
            <div>
            {participants.map(participant => (
                <div>
                {props.isCreator==true?(<p>{participant.username} <button onClick={()=>{checkInParticipant(participant.appUserId)}}>Check In</button></p>):(<p>{participant.username}</p>)}
                
                </div>
            ))}
               
            </div>
        </div>

    )

}