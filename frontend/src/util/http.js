import axios from "axios";
import domain from "./domain";
export default axios.create({
  baseURL: domain,
});
