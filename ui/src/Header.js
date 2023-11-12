import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Button from 'react-bootstrap/Button';
import { useNavigate } from "react-router-dom";

export function Header({accessToken}) {

    const navigate = useNavigate();

    const onClickLogin = () => {
      console.log("login button click!");
      console.log("accesstoken= " + accessToken)
      console.log("token in localstorage: " + localStorage.getItem("jwt_token"))
      navigate("/login")
    }

    const onCLickLogout = () => {
      fetch("http://localhost:8082/api/v1/logout")
      .then(response => {
        console.log(response)
        localStorage.setItem("jwt_token", "")
      })
    }

    const isActive = (path) => {
      return path === window.location.pathname
    }

    const btn = () => {
      if (localStorage.getItem("jwt_token") === "") return (
        <div><Button variant="outline-success" className="d-flex" onClick={onClickLogin}>login</Button></div>
      )
      else return (
        <div><Button variant="outline-success" className="d-flex" onClick={onCLickLogout}>logout</Button></div>
      )}

    return (
        <Navbar expand="lg" className="bg-body-tertiary">
          <Container>
            <Navbar.Brand href="/home">CoffeeDairy</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                {/* home page */}
                <Nav.Link href="/home"  active={isActive("/home")}>home</Nav.Link>
                {/* users page */}
                <Nav.Link href="/users" active={isActive("/users")}>users</Nav.Link>
                {/* admin page */}
                <Nav.Link href="/admin" active={isActive("/admin")}>admin</Nav.Link>
              </Nav>

              {btn()}

            </Navbar.Collapse>
          </Container>
        </Navbar>
    );
}