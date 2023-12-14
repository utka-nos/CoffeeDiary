import Container from 'react-bootstrap/Container';
import ListGroup from 'react-bootstrap/ListGroup';
import Button    from 'react-bootstrap/Button'
import Modal     from 'react-bootstrap/Modal';
import Form      from 'react-bootstrap/Form';
import { useEffect, useState } from 'react'

export function PerformancePage() {

  const [performances, setPerformances] = useState([]);
  const [showAddNewPerformanceModal, setShowAddNewPerformanceModal] = useState(false);
  const [currentAddPerformanceFieldNameValue, setCurrentAddPerformanceFieldNameValue] = useState("");

  useEffect(() => {
    updatePerformances();
  }, []);

  const updatePerformances = () => {
    console.log("getting performances...")
        fetch("http://localhost:8081/api/v1/performance/all", {
          method: "GET",
          headers: {
            "Authorization" : "Bearer " + localStorage.getItem("jwt_token")
          }
        })
          .then(body => body.json())
          .then(response => setPerformances(response))
  }

  const deletePerformance = (performanceId) => {
    fetch("http://localhost:8081/api/v1/performance/" + performanceId, {
      method: "DELETE",
      headers: {
        "Authorization" : "Bearer " + localStorage.getItem("jwt_token")
      }
    })
      .then(body => {
        if(body.status !== 200) console.log(body.status);
        else {
          console.log("deleted performance with id " + performanceId)
          updatePerformances();
        }
      })
  }

  const addNewPerformanceClickButton = () => {
    if(currentAddPerformanceFieldNameValue === "") return
    fetch("http://localhost:8081/api/v1/performance", {
      method: "POST",
      headers: {
        "Authorization" : "Bearer " + localStorage.getItem("jwt_token"),
        "Content-Type" : "application/json"
      },
      body: "{ \"name\": \"" + currentAddPerformanceFieldNameValue + "\" }"
    })
      .then(response => {
        updatePerformances();
        if(response.status !== 201) {
          console.error("error! status is " + response.status);
        }
        else return response.json()
      })
      .then(body => {
        console.log("saved performance: " + body)
        setShowAddNewPerformanceModal(false);
      })
  }

  const performanceRow = (perf) => {
    return (
      <ListGroup.Item key={perf.id} className="d-flex justify-content-between align-items-start" as="li">
        <div className="d-flex my-auto">
          <div className="my-auto me-2">{perf.id}</div>
          <div className="my-auto me-2">{perf.name}</div>
        </div>
        <Button onClick={() => deletePerformance(perf.id)}>delete</Button>
      </ListGroup.Item>
    )
  }

  const performanceList = () => {
    if(performances === []) return (<div>"loading..."</div>);
    else return (
    <div>
      <ListGroup>
        {performances.map(perf => performanceRow(perf))}
      </ListGroup>
    </div>
    )
  }

  const addNewPerformanceModal = () => (
    <Modal show={showAddNewPerformanceModal} onHide={() => setShowAddNewPerformanceModal(false)}>
      <Modal.Header closeButton>
        <Modal.Title>Add new performance</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Name of performance</Form.Label>
            <Form.Control type="text" onChange={(event) => setCurrentAddPerformanceFieldNameValue(event.target.value)}/>
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={() => setShowAddNewPerformanceModal(false)}>
          Close
        </Button>
        <Button variant="primary" onClick={() => addNewPerformanceClickButton()}>
          Save Changes
        </Button>
      </Modal.Footer>
    </Modal>
  )

  return(
    <Container className="mt-2">
      {addNewPerformanceModal()}
      performances:
      <Button className="mx-2 my-2" onClick={() => setShowAddNewPerformanceModal(true)}>Add new Performance</Button>
      {performanceList()}
    </Container>
  )
}