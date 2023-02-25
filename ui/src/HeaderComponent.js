import React from "react";
import Navbar from "react-bootstrap/Navbar"
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav"
import {Button} from "react-bootstrap";


export default class HeaderComponent extends React.Component {
    constructor(props) {
        super(props);

    }

    isActivePage = (path) => {
        const curPath = window.location.pathname;
        return path === curPath;
    }

    render() {
        return (
            <Navbar bg="light" variant="light ">
                <Container fluid>
                    <Navbar.Brand>CoffeeDairy</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="/" active={this.isActivePage("/")}>Home</Nav.Link>
                        <Nav.Link href="/dairy" active={this.isActivePage("/dairy")}>Dairy</Nav.Link>
                        <Nav.Link disabled="true">Map</Nav.Link>
                        <Nav.Link disabled="true">News</Nav.Link>
                    </Nav>
                    <Button className="d-flex">Sign in</Button>
                </Container>
            </Navbar>
        );
    }

}