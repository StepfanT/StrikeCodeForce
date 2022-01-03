import { Link } from "react-router-dom";

function NavBar() {
    return (
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
  
          <li>
            <Link to="/activity/view">View Activities</Link>
          </li>   
  
          <li>
            <Link to="/activity/contact">Contact Us</Link>
          </li>

          <li>
            <Link to="/activity/search">Search for Activities</Link>
          </li>
       
        </ul>
      </nav>
    );
  }
  
  export default NavBar;