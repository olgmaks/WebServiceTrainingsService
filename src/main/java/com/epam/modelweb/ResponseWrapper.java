package com.epam.modelweb;

import com.epam.model.Role;
import com.epam.model.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;

/**
 * Created by OLEG on 01.12.2015.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseWrapper {

	@XmlElementRefs({ @XmlElementRef(type = SummaryFault.class), @XmlElementRef(type = SummarySuccess.class) })
	private Summary summary;

	@XmlElementRefs({ @XmlElementRef(type = Role.class), @XmlElementRef(type = User.class) })
	private Object result;

	@XmlElementRefs({ @XmlElementRef(type = Role.class), @XmlElementRef(type = User.class) })
	private Object[] results;

	public ResponseWrapper() {
	}

	public ResponseWrapper(Summary summary) {
		this.summary = summary;
	}

	public ResponseWrapper(Summary summary, Object result) {
		this.summary = summary;
		this.result = result;
	}

	public ResponseWrapper(Summary summary, Object[] results) {
		this.summary = summary;
		this.results = results;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object[] getResults() {
		return results;
	}

	public void setResults(Object[] results) {
		this.results = results;
	}

	public static ResponseWrapper success(String msg) {
		return new ResponseWrapper(new SummarySuccess(msg));
	}

	public static ResponseWrapper success(String msg, Object result) {
		return new ResponseWrapper(new SummarySuccess(msg), result);
	}

	public static ResponseWrapper success(String msg, Object[] results) {
		return new ResponseWrapper(new SummarySuccess(msg), results);
	}

	public static ResponseWrapper fault(String msg) {
		return new ResponseWrapper(new SummaryFault(msg));
	}

	public static ResponseWrapper fault(String msg, Object result) {
		return new ResponseWrapper(new SummaryFault(msg), result);
	}

	public static ResponseWrapper fault(String msg, Object[] results) {
		return new ResponseWrapper(new SummaryFault(msg), results);
	}

}
