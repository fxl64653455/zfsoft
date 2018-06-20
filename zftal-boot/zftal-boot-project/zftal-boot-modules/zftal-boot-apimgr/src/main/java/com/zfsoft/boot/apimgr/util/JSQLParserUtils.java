/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Limit;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.Top;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.AddAliasesVisitor;
import net.sf.jsqlparser.util.TablesNamesFinder;

@SuppressWarnings("unused")
public class JSQLParserUtils {

	private static final TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();

	private static final AddAliasesVisitor instance = new AddAliasesVisitor();

	public static List<String> getAllColumnNames(String sql) throws JSQLParserException {
		Statement statement = JSQLParserUtils.getStatement(sql);
		return getAllColumnNames( statement);
	}
	
	public static List<String> getAllColumnNames(Statement statement) throws JSQLParserException {
		
		List<String> columnList = new ArrayList<String>();
		// insert case
		if (statement instanceof Insert) {
			// 获取新增字段列名称
			columnList.addAll( getColumnNames((Insert) statement));
		} 
		// delete case
		else if (statement instanceof Delete) {
			// 获得where条件中的列
			columnList.addAll(getConditionColumnNames(statement));
		}
		// update case
		else if (statement instanceof Update) {
			// 获取变更字段列名称
			columnList.addAll( getColumnNames((Update) statement));
			// 获得where条件中的列名称
			columnList.addAll(getConditionColumnNames(statement));
		} 
		// select case
		else if (statement instanceof Select) {
			// 获取查询字段列名称
			columnList.addAll( getColumnNames((Select) statement));
			// 获得where条件中的列名称
			columnList.addAll(getConditionColumnNames(statement));
		}
		return columnList;
	}
	
	public static List<String> getColumnNames(Insert statement) throws JSQLParserException {
		List<String> columnList = new ArrayList<String>();
		Insert insert =  (Insert) statement;
		List<Column> columns = insert.getColumns();
		for (Column column : columns) {
			columnList.add(column.getColumnName());
		}
		return columnList;
	}
	 
	public static List<String> getColumnNames(Update statement) throws JSQLParserException {
		List<String> columnList = new ArrayList<String>();
		List<Column> columns = statement.getColumns();
		for (Column column : columns) {
			columnList.add(column.getColumnName());
		}
        return columnList;
	}
	
	public static List<String> getColumnNames(Select statement) throws JSQLParserException {
		List<String> columnList = new ArrayList<String>();
		PlainSelect plainSelect = (PlainSelect) statement.getSelectBody();
		// 解析查询列
		for (Object element : plainSelect.getSelectItems()) {
			if(element instanceof SelectExpressionItem){
				SelectExpressionItem item = (SelectExpressionItem) element;
				if(null!= item.getAlias() && StringUtils.hasText(item.getAlias().getName())){
					columnList.add(item.getAlias().getName());
				}else{
					Column column = (Column) item.getExpression();
					columnList.add(column.getColumnName());
				}
			}
		}
        return columnList;
	}
	
	public static List<String> getConditionColumnNames(String sql) throws JSQLParserException {
		Statement statement = JSQLParserUtils.getStatement(sql);
		return getConditionColumnNames( statement);
	}
	
	public static List<String> getConditionColumnNames(Statement statement) throws JSQLParserException {
		
		List<String> columnList = new ArrayList<String>();
		// insert case
		if (statement instanceof Insert) {
			// nothing
		} 
		// delete case
		else if (statement instanceof Delete) {
            return getConditionColumnNames((Delete) statement);
		}
		// update case
		else if (statement instanceof Update) {
			return getConditionColumnNames((Update) statement);
		} 
		// select case
		else if (statement instanceof Select) {
			return getConditionColumnNames((Select) statement);
		}
		return columnList;
	}
	
	public static List<String> getConditionColumnNames(Delete statement) throws JSQLParserException {
		List<String> columnList = new ArrayList<String>();
		//获得where条件表达式
        Expression where = statement.getWhere();
        //BinaryExpression包括了整个where条件，
        //例如：AndExpression/LikeExpression/OldOracleJoinBinaryExpression
        if(where instanceof BinaryExpression){
        	columnList = getColumnNames((BinaryExpression)(where), columnList);
        }
        return columnList;
	}
	
	public static List<String> getConditionColumnNames(Update statement) throws JSQLParserException {
		List<String> columnList = new ArrayList<String>();
		//获得where条件表达式
        Expression where = statement.getWhere();
        //BinaryExpression包括了整个where条件，
        //例如：AndExpression/LikeExpression/OldOracleJoinBinaryExpression
        if(where instanceof BinaryExpression){
        	columnList = getColumnNames((BinaryExpression)(where), columnList);
        }
        return columnList;
	}
	
	public static List<String> getConditionColumnNames(Select statement) throws JSQLParserException {
		List<String> columnList = new ArrayList<String>();
		PlainSelect plainSelect = (PlainSelect) statement.getSelectBody();
		//获得where条件表达式
        Expression where = plainSelect.getWhere();
        //BinaryExpression包括了整个where条件，
        //例如：AndExpression/LikeExpression/OldOracleJoinBinaryExpression
        if(where instanceof BinaryExpression){
        	columnList = getColumnNames((BinaryExpression)(where), columnList);
        }
        return columnList;
	}

	public static List<String> getTables(String sql) throws JSQLParserException {
		Statement statement = CCJSqlParserUtil.parse(sql);
		if (statement != null) {
			return tablesNamesFinder.getTableList(statement);
		}
		return null;
	}

	public static Top getTop(String sql) throws JSQLParserException {
		Statement statement = JSQLParserUtils.getStatement(sql);
		if (statement instanceof Select) {
			SelectBody select = ((Select) statement).getSelectBody();
			if (select instanceof PlainSelect) {
				return ((PlainSelect) select).getTop();
			}
		}
		return null;
	}

	public static Limit getLimit(String sql) throws JSQLParserException {
		Statement statement = JSQLParserUtils.getStatement(sql);
		if (statement instanceof Select) {
			SelectBody select = ((Select) statement).getSelectBody();
			if (select instanceof PlainSelect) {
				return ((PlainSelect) select).getLimit();
			}
		}
		return null;
	}

	public static Statement getStatement(String sql) throws JSQLParserException {
		return CCJSqlParserUtil.parse(sql);
	}

	/**
	 * @description ： 获得where条件字段中列名
	 * @param expression
	 * @param allColumnNames
	 * @return
	 */
	private static List<String> getColumnNames(Expression expression, List<String> allColumnNames) {
		String columnName = null;
		if (expression instanceof BinaryExpression) {
			// 获得左边表达式
			Expression leftExpression = ((BinaryExpression) expression).getLeftExpression();
			// 如果左边表达式为Column对象，则直接获得列名
			if (leftExpression instanceof Column) {
				// 获得列名
				columnName = ((Column) leftExpression).getColumnName();
				allColumnNames.add(columnName);
				// 拼接操作符
				// ((BinaryExpression) expression).getStringExpression();
			}
			// 否则，进行迭代
			else if (leftExpression instanceof BinaryExpression) {
				getColumnNames((BinaryExpression) leftExpression, allColumnNames);
			}

			// 获得右边表达式，并分解
			Expression rightExpression = ((BinaryExpression) expression).getRightExpression();
			if (rightExpression instanceof BinaryExpression) {
				Expression leftExpression2 = ((BinaryExpression) rightExpression).getLeftExpression();
				if (leftExpression2 instanceof Column) {
					// 获得列名
					columnName = ((Column) leftExpression2).getColumnName();
					allColumnNames.add(columnName);
					// 获得操作符
					// ((BinaryExpression) rightExpression).getStringExpression();
				}
			}
		}
		return allColumnNames;
	}

}
