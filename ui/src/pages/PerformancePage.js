import Container from 'react-bootstrap/Container';
import ListGroup from 'react-bootstrap/ListGroup';
import Button    from 'react-bootstrap/Button'
import { useEffect, useState } from 'react'

export function PerformancePage() {

  const [performances, setPerformances] = useState([]);

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

  const performanceRow = (perf) => {
    return (
      <ListGroup.Item key={perf.id}>
        {perf.name}
        <Button onClick={() => deletePerformance(perf.id)}>deleteMe</Button>
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

  return(
    <Container className="mt-2">
      performances:
      {performanceList()}
    </Container>
  )
}