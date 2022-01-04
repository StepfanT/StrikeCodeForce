import { Link } from "react-router-dom";

function NavBar() {
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>

        <li>
          <Link to="/activity/contact">Contact Us</Link>
        </li>

        <li>
          <Link to="/activity/view">View Activities</Link>
        </li>

        <li>
          <Link to="/activity/browse">Browse Activities</Link>
        </li>

        <li>
          <Link to="/activity/create">Create An Activity</Link>
        </li>

        <li>
          <Link to="/activity/points">View Points</Link>
        </li>

      </ul>
    </nav>
  );
}

export default NavBar;