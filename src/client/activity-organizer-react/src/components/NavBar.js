import { Link } from "react-router-dom";


export default function NavBar(userStatus) {
  return (
    <div>
      <nav
        style={{
          borderBottom: "solid 1px",
          paddingBottom: "1rem"
        }}
      >
        {userStatus.user ? (
          <button onClick={userStatus.logout}>
            Logout {userStatus.user.sub}
          </button>

        ) : (
          <Link to="/login">Login</Link>
        )}

        |{" "}  <Link to="/activity">View Activities</Link> |{" "}
        <Link to="/activity/browse">Browse Activities</Link> |{" "}
        <Link to="/activity/create">Create An Activity</Link> |{" "}
        <Link to="/activity/points">View Points</Link> |{" "}
        <Link to="/home">About Us</Link> |{" "}
        <Link to="/contact">Contact</Link> |{" "}
        <Link to="/dashboard">Dashboard</Link> |{" "}

      </nav>
    </div>
  );
}