<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:include href="stylesheets/common.xslt"/>

<xsl:template match="/ROOT/COREF"/>

<xsl:template match="/ROOT/*" priority="0">
	Unknown(
	<xsl:for-each select=".//*[@id]">
		<xsl:value-of select="."/><xsl:text> </xsl:text>
    </xsl:for-each>)
</xsl:template>

<xsl:template match="/ROOT/S[count(S)>0]">
    <xsl:for-each select="S">
        <xsl:apply-templates select="."/>
    </xsl:for-each>
</xsl:template>

<!-- axioms -->
<xsl:include href="stylesheets/axioms/equivalentClasses.xslt"/>
<xsl:include href="stylesheets/axioms/classAssertion.xslt"/>
<xsl:include href="stylesheets/axioms/objectPropertyAssertion.xslt"/>
<xsl:include href="stylesheets/axioms/objectPropertyDomainAndRange.xslt"/>
<xsl:include href="stylesheets/axioms/subClassOf.xslt"/>
<xsl:include href="stylesheets/axioms/disjointClasses.xslt"/>
<!-- queries -->
<xsl:include href="stylesheets/queries/isDirectSubClassOf.xslt"/>
<xsl:include href="stylesheets/queries/isInstanceOf.xslt"/>
<xsl:include href="stylesheets/queries/getObjectPropertyValues.xslt"/>
<xsl:include href="stylesheets/queries/getInstances.xslt"/>
<!-- phrases -->
<xsl:include href="stylesheets/expressions/objectIntersectionOf.xslt"/>
<xsl:include href="stylesheets/expressions/objectUnionOf.xslt"/>
<xsl:include href="stylesheets/expressions/objectSomeValuesFrom.xslt"/>
<xsl:include href="stylesheets/expressions/objectMinCardinality.xslt"/>

<xsl:include href="stylesheets/nounphrase.xslt"/>
 
<xsl:template match="text()|@*"/>

</xsl:stylesheet>