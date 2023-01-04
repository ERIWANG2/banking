package rdc.ericwangi.banking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OperationNonPermittedException extends RuntimeException {

    private final String erreurMessage;

    private final String operationId;

    private final String source;

    private final String dependency;



}
