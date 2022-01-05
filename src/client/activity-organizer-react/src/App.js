import { Link } from "react-router-dom";

export default function App() {
  return (
    <div>
      <h1>Welcome to Groop Organizer!</h1>
      <nav
        style={{
          borderBottom: "solid 1px",
          paddingBottom: "1rem"
        }}
      >
        <Link to="/activity">View Activities</Link> |{" "}
        <Link to="/activity/browse">Browse Activities</Link> |{" "}
        <Link to="/activity/create">Create An Activity</Link> |{" "}
        <Link to="/activity/points">View Points</Link> |{" "}
        <Link to="/home">About Us</Link> |{" "}
        <Link to="/contact">Contact</Link>
        <Link to="/test">Test</Link>

      </nav>
    </div>
  );
}